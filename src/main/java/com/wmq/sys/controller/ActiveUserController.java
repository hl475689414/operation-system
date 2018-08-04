package com.wmq.sys.controller;

import com.wmq.sys.service.ActiveUserService;
import com.wmq.sys.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 程江涛 on 2018/5/22 0022
 */
@RestController
@RequestMapping("/activeUser")
public class ActiveUserController extends BaseController {
    @Autowired
    private ActiveUserService activeUserService;

    /**
     * 获取活跃用户数据
     * @return
     */
    @PostMapping(value = "/getActiveUserData")
    @RequiresPermissions("sys:operations:seeData")
    public JsonResult getActiveUserData() {
        try{
            return activeUserService.getActiveUserData();
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

}
