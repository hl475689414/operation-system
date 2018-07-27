package com.wmq.sys.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.utils.Constants;
import com.wmq.sys.utils.JsonResult;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by 李怀鹏 on 2018/6/26.
 * 登录控制器
 */
public class KickoutSessionControlFilter extends AccessControlFilter {
    @Value("${shiro_redis_prefix}")
    private String shiro_redis_prefix;

    @Autowired
    private RedisSessionDAO redisSessionDAO;

    private String kickoutUrl; //踢出后到的地址
    private boolean kickoutAfter = false; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private int maxSession = 1; //同一个帐号最大会话数 默认1

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    //设置Cache的key的前缀
    public void setCacheManager(RedisCacheManager redisCacheManager) {
        this.cache = redisCacheManager.getCache(shiro_redis_prefix);
    }

    /**
     * 表示是否允许访问；mappedValue 就是[urls]配置中拦截器参数部分，如果允许访问返回 true，否则 false，此处直接返回false，让流程走到onAccessDenied去控制
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        //获取请求路径
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httprequest = requestAttributes.getRequest();
        HttpServletResponse httpresponse = requestAttributes.getResponse();
        String path = httprequest.getServletPath();
        String fullPath = httprequest.getRequestURL().toString().split("\\?")[0];
        //如果已登录，再次请求登录接口，直接进行登录流程
        if(path.contains("/common/login")) {
            return true;
        }

        Session session = subject.getSession();
        SystemUser user = (SystemUser) subject.getPrincipal();
        String userkey = "operatrion-"+user.getId();
        Serializable sessionId = session.getId();

        //读取缓存   没有就存入
        Deque<Serializable> deque = cache.get(userkey);

        //如果此用户没有session队列，也就是还没有登录过，缓存中没有
        //就new一个空队列，不然deque对象为空，会报空指针
        if(deque==null){
            deque = new LinkedList<Serializable>();
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            //将sessionId存入队列
            deque.push(sessionId);
            //将用户的sessionId队列缓存
            cache.put(userkey, deque);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while(deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            if(kickoutAfter) { //如果踢出后者
                kickoutSessionId = deque.removeFirst();
                //踢出后再更新下缓存队列
                cache.put(userkey, deque);
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast();
                //踢出后再更新下缓存队列
                cache.put(userkey, deque);
            }

            try {
                //获取被踢出的sessionId的session对象
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                    redisSessionDAO.doDelete(kickoutSession); //清除缓存中的会话信息
                }
            } catch (Exception e) {//ignore exception
            }
        }

        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
            //会话被踢出了
            try {
                //退出登录
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(request);

            //前后端分离，所以直接返回JSON
            JsonResult jsonResult = new JsonResult();
            jsonResult.setCode(Constants.TOKEN_KICKOUT);
            jsonResult.setMessage("您已经在其他地方登录，请重新登录！");
            ObjectMapper objectMapper = new ObjectMapper();
            simpleRender(httpresponse, objectMapper.writeValueAsString(jsonResult));
            //重定向
//          WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
        }
        return true;
    }

    /**
     * 渲染输出流
     *
     * @param response {@link HttpServletResponse}
     * @param out      输出信息
     * @throws IOException see {@link ServletResponse#getWriter()}
     */
    private void simpleRender(HttpServletResponse response, String out) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("expires", "0");
        response.setContentType("text/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(out);
        printWriter.flush();
    }
}
