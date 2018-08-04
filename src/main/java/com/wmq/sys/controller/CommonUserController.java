package com.wmq.sys.controller;

import com.wmq.sys.service.CommonUserService;
import com.wmq.sys.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 李怀鹏 on 2018/5/14.
 */
@RestController
@RequestMapping("/user")
public class CommonUserController extends BaseController {
    @Autowired
    private CommonUserService commonUserService;

    /**
     * 获取用户列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getCommonUserList")
    @RequiresPermissions("sys:user:userList")
    public JsonResult getCommonUserList(HttpServletRequest request, HttpServletResponse response) {
        String keys = getParams().getString("keys"); //搜索关键字
        int sex = getParams().getInt("sex"); //性别
        int state = getParams().getInt("state"); //状态
        int isCompany = getParams().getInt("isCompany"); //有无企业 0无 1有
        return commonUserService.getCommonUserList(keys, sex, state, isCompany);
    }

    /**
     * 获取用户信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getUserInfo")
    @RequiresPermissions("sys:user:userInfo")
    public JsonResult getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        int userId = getParams().getInt("userId");
        return commonUserService.getUserInfo(userId);
    }

    /**
     * 获取个人信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getPersonalUserInfo")
    @RequiresPermissions("sys:user:userInfo")
    public JsonResult getPersonalUserInfo(HttpServletRequest request, HttpServletResponse response) {
        int userId = getParams().getInt("userId");
        return commonUserService.getPersonalUserInfo(userId);
    }

    /**
     * 获取企业信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getCompanyUserInfo")
    @RequiresPermissions("sys:user:userInfo")
    public JsonResult getCompanyUserInfo(HttpServletRequest request, HttpServletResponse response) {
        int userId = getParams().getInt("userId");
        return commonUserService.getCompanyUserInfo(userId);
    }
}