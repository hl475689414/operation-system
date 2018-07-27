package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/5/14.
 */
public interface CommonService {
    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    JsonResult login(String account, String password);

    /**
     * 获取所有职位分类
     * @return
     */
    JsonResult getJobClassList();

    /**
     * 获取所有行业分类
     * @return
     */
    JsonResult getBusinessList();

    /**
     * 获取所有城市分类
     * @return
     */
    JsonResult getCityList();
}
