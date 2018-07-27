package com.wmq.sys.service.impl;

import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.utils.base.Base;
import com.wmq.sys.utils.mybatis.paginatc.PageContext;
import com.wmq.sys.utils.mybatis.paginatc.domain.PageBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by 李怀鹏 on 2018/1/19.
 */
public class BaseService extends Base {
    //设置当前页
    public void setToPage(int toPage) {
        PageContext.getPage().setPage(toPage);
    }

    //设置页大小
    public void setPageSize(int pageSize) {
        PageContext.getPage().setLimit(pageSize);
    }

    //设置页大小
    public int getPageSize() {
        return PageContext.getPage().getLimit();
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
    public int getMaxPage() {
        return getPage().getPaginator().getTotalPages();
    }

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
