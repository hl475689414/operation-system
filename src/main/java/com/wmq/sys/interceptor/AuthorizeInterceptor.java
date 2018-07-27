package com.wmq.sys.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmq.sys.redis.RedisCache;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.utils.RequestUtils;
import com.wmq.sys.utils.base.Base;
import com.wmq.sys.utils.mybatis.paginatc.PageContext;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 李怀鹏 on 2018/4/25.
 */
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(AuthorizeInterceptor.class);

    @Autowired
    private RedisCache redisCache;

    @Value("${loginJudge}")
    private int loginJudge;

    //拦截请求参数
    private static final ThreadLocal parameterJson = new ThreadLocal();
    public static void setParameterJson(String dataSourceType) {
        parameterJson.set(dataSourceType);
    }
    public static String getParameterJson() {
        return (String) parameterJson.get();
    }

    /**
     * 在controller调用之前调用此方法，判断权限
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //获取请求参数并封装为JSON
//        String jsonStr = RequestUtils.getParamsJsonStr(request);
//        setParameterJson(jsonStr);

        //拦截页数以及条数进行分页
        Object toPage = JSONObject.fromObject(AuthorizeInterceptor.getParameterJson()).get("toPage");
        if (toPage != null) {
            PageContext.getPage().setPage((Integer) toPage);
            Object limit = JSONObject.fromObject(AuthorizeInterceptor.getParameterJson()).get("limit");
            if(limit != null && (Integer)limit > 0) {
                PageContext.getPage().setLimit((Integer) limit);
            }else {
                PageContext.getPage().setLimit(20);
            }
            if (JSONObject.fromObject(AuthorizeInterceptor.getParameterJson()).get("limit") != null) {
                PageContext.getPage().setLimit(JSONObject.fromObject(AuthorizeInterceptor.getParameterJson()).getInt("limit"));
            }
            PageContext.getPage().setContainsTotalCount(true);
        }

        return true;
    }

    /**
     * 在controller方法调用之后调用此方法，但是但是在视图被渲染之前调用，进行请求拦截
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        System.out.println("请求处理之后进行调用，但是在视图被渲染之前(Controller方法调用之后)");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println("在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行(主要是用于进行资源清理工作)");
    }

    /**
     * 渲染输出流
     *
     * @param response {@link HttpServletResponse}
     * @param out      输出信息
     * @throws IOException see {@link javax.servlet.ServletResponse#getWriter()}
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
