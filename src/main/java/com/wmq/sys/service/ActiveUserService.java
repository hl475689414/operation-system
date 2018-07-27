package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 程江涛 on 2018/5/22 0022
 */
public interface ActiveUserService {

    /**
     * 获取活跃用户数据
     * @return
     */
    JsonResult getActiveUserData();

}