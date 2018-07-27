package com.wmq.sys.shiro;

import com.wmq.sys.interceptor.AuthorizeInterceptor;
import com.wmq.sys.utils.RequestUtils;
import com.wmq.sys.utils.StringUtils;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by 李怀鹏 on 2018/6/28.
 * 重写shiro的获取sessionid方法，获取token值重新赋给shiro的session，否则没有cookie，每次都是新的sessionId，将不能保持登录状态
 * 获取传过来的JSON串判断token，token为sessionId
 * 其实这里还可以使用如下参数：cookie中的session名称：如：JSESSIONID=xxx,路径中的 ;JESSIONID=xxx，但建议还是使用token。
 * 此处重写只希望处理session会话问题，其它工作继续交由自定义拦截器处理,使用此方法是需要把自定义拦截器中获取JSON参数的方法去掉，否则参数无法获取
 */
@Component
public class SessionManager extends DefaultWebSessionManager {
    //拦截请求参数
    private static final ThreadLocal parameterJson = new ThreadLocal();
    public static void setParameterJson(String dataSourceType) {
        parameterJson.set(dataSourceType);
    }
    public static String getParameterJson() {
        return (String) parameterJson.get();
    }

    public SessionManager() {
        super();
    }

    /**
     * 重写shiro获取sessionId方法
     * 不是前后端完全分离时注释此方法即可使用cookie方式
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求参数并封装为JSON
        HttpServletRequest httpServletRequestrequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String jsonStr = RequestUtils.getParamsJsonStr(httpServletRequestrequest);
        //此方法会调用两次，第二次调用是会获取不到requet的请求参数，原因未知，所以为空时不重新赋值
        if(StringUtils.notEmpty(jsonStr) && !jsonStr.equals("{}")) {
            setParameterJson(jsonStr);
            //将JSON串参数赋值给自定义拦截器，以便做其它工作
            AuthorizeInterceptor.setParameterJson(jsonStr);
        }
        Object token = null;
        try {
            token = JSONObject.fromObject(getParameterJson()).get("token");
        }catch (JSONException e) {
            //忽略此异常
        }
        if (StringUtils.notEmpty(token)) {
            // 设置当前session状态
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    ShiroHttpServletRequest.URL_SESSION_ID_SOURCE); // session来源与url
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token.toString());
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token.toString();
        }else {
            return super.getSessionId(request, response);
        }
    }

    /**
     * 获取session
     * 优化单次请求需要多次访问redis的问题
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);

        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        if (request != null && null != sessionId) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
                return (Session) sessionObj;
            }
        }

        Session session = super.retrieveSession(sessionKey);
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
