package com.wmq.sys.service;

import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/7/9.
 */
public interface SystemUserService {
    /**
     * 验证旧密码是否正确
     * @param sysUserId
     * @param password
     * @return
     */
    JsonResult verificationPassword(int sysUserId, String password);

    /**
     * 修改密码
     * @param sysUserId
     * @param password
     * @param token
     * @return
     */
    JsonResult modifyPassWord(int sysUserId, String password, String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    JsonResult signOutLogin(String token);

    /**
     * 获取平台账号列表
     * @param key
     * @return
     */
    JsonResult getSysUserListPage(String key);

    /**
     * 新增账号
     * @param systemUser
     * @return
     */
    JsonResult addSysUser(SystemUser systemUser);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    JsonResult getSysUserInfo(int id);

    /**
     * 编辑用户
     * @param systemUser
     * @return
     */
    JsonResult editSysUser(SystemUser systemUser);

    /**
     * 删除用户
     * @param id
     * @return
     */
    JsonResult deleteSysUser(int id);

    /**
     * 禁用用户
     * @param id
     * @return
     */
    JsonResult disableSysUser(int id);

    /**
     * 启用用户
     * @param id
     * @return
     */
    JsonResult enableSysUser(int id);

    /**
     * 重置密码
     * @param id
     * @return
     */
    JsonResult resetPassword(int id);
}
