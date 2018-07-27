package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/5/14.
 */
public interface CommonUserService {
    /**
     * 获取用户列表
     * @param keys
     * @param sex
     * @param state
     * @param isCompany
     * @return
     */
    JsonResult getCommonUserList(String keys, int sex, int state, int isCompany);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    JsonResult getUserInfo(int userId);

    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    JsonResult getPersonalUserInfo(int userId);

    /**
     * 获取企业信息
     * @param userId
     * @return
     */
    JsonResult getCompanyUserInfo(int userId);
}
