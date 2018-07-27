package com.wmq.sys.controller;

import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.service.SystemUserService;
import com.wmq.sys.utils.Constants;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.utils.MD5Util;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by 李怀鹏 on 2018/5/14.
 */
@RestController
@RequestMapping("/sys")
public class SysUserController extends BaseController {
    @Autowired
    private SystemUserService systemUserService;

    /**
     * 验证码旧密码是否正确
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/verificationPassword")
    public JsonResult verificationPassword(HttpServletRequest request, HttpServletResponse response) {
        int sysUserId = Integer.parseInt(getParams().getString("sysUserId"));
        String password = getParams().getString("password");
        return systemUserService.verificationPassword(sysUserId, password);
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/modifyPassWord")
    public JsonResult modifyPassWord(HttpServletRequest request, HttpServletResponse response) {
        int sysUserId = Integer.parseInt(getParams().getString("sysUserId"));
        String password = getParams().getString("password");
        String token = getParams().getString("token");
        return systemUserService.modifyPassWord(sysUserId, password, token);
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/signOutLogin")
    public JsonResult signOutLogin(HttpServletRequest request, HttpServletResponse response) {
        String token = getParams().getString("token");
        return systemUserService.signOutLogin(token);
    }

    /**
     * 踢出登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/kickout")
    public JsonResult kickout(HttpServletRequest request, HttpServletResponse response) {
        return new JsonResult(Constants.TOKEN_KICKOUT, "您已经在其他地方登录，请重新登录！");
    }

    /**
     * 获取平台账号列表
     * @return
     */
    @RequestMapping("/getSysUserList")
    @RequiresPermissions("sys:sysUser:userList")
    public JsonResult getSysUserList() {
        String key = null; //搜索关键字
        if(getParams().containsKey("key")) {
            key = getParams().getString("key");
        }
        return systemUserService.getSysUserListPage(key);
    }

    /**
     * 新增账号
     * @return
     */
    @RequestMapping("/addSysUser")
    @RequiresPermissions("sys:sysUser:addUser")
    public JsonResult addSysUser() {
        String userName = getParams().getString("userName");
        String account = getParams().getString("account");
        String passWord = null;
        if(getParams().containsKey("passWord")) {
            passWord = getParams().getString("passWord");
        }
        String mobile = null;
        if(getParams().containsKey("mobile")) {
            mobile = getParams().getString("mobile");
        }
        int deptId = getParams().getInt("deptId");
        int roleId = getParams().getInt("roleId");
        SystemUser systemUser = new SystemUser();
        systemUser.setAccount(account);
        systemUser.setUserName(userName);
        systemUser.setPassWord(notEmpty(passWord) ? MD5Util.MD5(passWord) : MD5Util.MD5("123456"));
        systemUser.setDeptId(deptId);
        systemUser.setRoleId(roleId);
        if(notEmpty(mobile)) {
            systemUser.setMobile(mobile);
        }
        systemUser.setStatus(0);
        systemUser.setUserIdCreate(super.getSysUser().getId());
        systemUser.setCreateTime(new Date());
        systemUser.setModifiedTime(new Date());
        systemUser.setDelFlag(0);
        return systemUserService.addSysUser(systemUser);
    }

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping("/getSysUserInfo")
    @RequiresPermissions("sys:sysUser:editUser")
    public JsonResult getSysUserInfo() {
        int id = getParams().getInt("id");
        return systemUserService.getSysUserInfo(id);
    }

    /**
     * 编辑用户
     * @return
     */
    @RequestMapping("/editSysUser")
    @RequiresPermissions("sys:sysUser:editUser")
    public JsonResult editSysUser() {
        int id = getParams().getInt("id");
        String userName = getParams().getString("userName");
        String account = getParams().getString("account");
        String passWord = null;
        if(getParams().containsKey("passWord")) {
            passWord = getParams().getString("passWord");
        }
        String mobile = null;
        if(getParams().containsKey("mobile")) {
            mobile = getParams().getString("mobile");
        }
        int deptId = getParams().getInt("deptId");
        int roleId = getParams().getInt("roleId");
        SystemUser systemUser = new SystemUser();
        systemUser.setId(id);
        systemUser.setAccount(account);
        systemUser.setUserName(userName);
        systemUser.setPassWord(notEmpty(passWord) ? passWord : "123456");
        systemUser.setDeptId(deptId);
        systemUser.setRoleId(roleId);
        if(notEmpty(mobile)) {
            systemUser.setMobile(mobile);
        }
        systemUser.setModifiedTime(new Date());
        return systemUserService.editSysUser(systemUser);
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping("/deleteSysUser")
    @RequiresPermissions("sys:sysUser:deleteUser")
    public JsonResult deleteSysUser() {
        int id = getParams().getInt("id");
        return systemUserService.deleteSysUser(id);
    }

    /**
     * 禁用用户
     * @return
     */
    @RequestMapping("/disableSysUser")
    @RequiresPermissions("sys:sysUser:disableUser")
    public JsonResult disableSysUser() {
        int id = getParams().getInt("id");
        return systemUserService.disableSysUser(id);
    }

    /**
     * 启用用户
     * @return
     */
    @RequestMapping("/enableSysUser")
    @RequiresPermissions("sys:sysUser:enableUser")
    public JsonResult enableSysUser() {
        int id = getParams().getInt("id");
        return systemUserService.enableSysUser(id);
    }

    /**
     * 启用用户
     * @return
     */
    @RequestMapping("/resetPassword")
    @RequiresPermissions("sys:sysUser:resetPassword")
    public JsonResult resetPassword() {
        int id = getParams().getInt("id");
        return systemUserService.resetPassword(id);
    }
}
