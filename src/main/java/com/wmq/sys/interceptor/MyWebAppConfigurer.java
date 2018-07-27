package com.wmq.sys.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by 李怀鹏 on 2018/4/25.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthorizeInterceptor authorizeInterceptor;  //此处如果不使用注入方式，会导致AuthorizeInterceptor类所有注入失效

    /**
     * 添加拦截路径配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 多个拦截器组成一个拦截器链
         * addPathPatterns 用于添加拦截规则
         * excludePathPatterns 用于排除拦截,此处排除拦截后将不会进入AuthorizeInterceptor中的preHandle方法，如果获取参数为封装JSON，会导致无法获取参数
         * addInterceptor（new AuthorizeInterceptor）不使用注入方式时可以用此方式，但会导致AuthorizeInterceptor类中所有注入失效
         */
        registry.addInterceptor(authorizeInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
