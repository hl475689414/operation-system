package com.wmq.sys.controller;

import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.interceptor.AuthorizeInterceptor;
import com.wmq.sys.utils.JsonUtils;
import com.wmq.sys.utils.base.Base;
import com.wmq.sys.utils.mybatis.paginatc.PageContext;
import com.wmq.sys.utils.mybatis.paginatc.domain.PageBounds;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by 李怀鹏 on 2018/4/18.
 */
public class BaseController extends Base {
    //-------------------------获取拦截器中封装好的请求参数JSON---------------------------------------------
    public JSONArray getParamArrs() {
        return JSONArray.fromObject(AuthorizeInterceptor.getParameterJson());
    }

    public JSONObject getParams() {
        return JSONObject.fromObject(AuthorizeInterceptor.getParameterJson());
    }

    public  String getParamsStr() {
        return AuthorizeInterceptor.getParameterJson();
    }

    public  <T> T getParams(Class<T> t) {
        try {
            return JsonUtils.JsonToBean(AuthorizeInterceptor.getParameterJson(), t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置分页信息，
     *
     * @param pageNo   页码
     * @param pageSize 页大小
     */
    public void setPage(int pageNo, int pageSize) {
        PageContext.getPage().setPage(pageNo);
        PageContext.getPage().setLimit(pageSize);
    }

    //设置当前页
    public void setPageNo(int pageNo) {
        PageContext.getPage().setPage(pageNo);
    }

    //设置页大小
    public void setPageSize(int pageSize) {
        PageContext.getPage().setLimit(pageSize);
    }

    //获取分页对象
    public PageBounds getPage() {
        return PageContext.getPage();
    }

    //获取当前页
    public int getPageNo() {
        return getPage().getPage();
    }

    //总记录数
    public int getTotalCount() {
        return getPage().getPaginator().getTotalCount();
    }

    //总页数
    public int getMaxPage() { return getPage().getPaginator().getTotalPages(); }

    /**
     * 获取当前登录的用户信息
     * @return
     */
    public SystemUser getSysUser() {
        Subject subject = SecurityUtils.getSubject();
        SystemUser user = (SystemUser) subject.getPrincipal();
        return user;
    }
}
