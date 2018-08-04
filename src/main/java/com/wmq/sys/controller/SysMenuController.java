package com.wmq.sys.controller;

import com.wmq.sys.entity.SystemMenu;
import com.wmq.sys.service.SystemMenuService;
import com.wmq.sys.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by 李怀鹏 on 2018/7/16.
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SystemMenuService systemMenuService;

    /**
     * 获取平台菜单列表
     * @return
     */
    @RequestMapping("/getSysMenuList")
    @RequiresPermissions("sys:sysMenu:menuList")
    public JsonResult getSysMenuList() {
        return systemMenuService.getSysMenuList();
    }

    /**
     * 新增菜单
     * @return
     */
    @RequestMapping("/addSysMenu")
    @RequiresPermissions("sys:sysMenu:addMenu")
    public JsonResult addSysMenu() {
        int parentId = 0;
        if(getParams().containsKey("parentId")) {
            parentId = getParams().getInt("parentId");
        }
        int type = getParams().getInt("type");
        String name = getParams().getString("name");
        String url = null;
        if(getParams().containsKey("url")) {
            url = getParams().getString("url");
        }
        String perms = null;
        if(getParams().containsKey("perms")) {
            perms = getParams().getString("perms");
        }
        String icon = null;
        if(getParams().containsKey("icon")) {
            icon = getParams().getString("icon");
        }
        SystemMenu systemMenu = new SystemMenu();
        systemMenu.setParentId(parentId);
        systemMenu.setName(name);
        systemMenu.setUrl(url);
        systemMenu.setPerms(perms);
        systemMenu.setType(type);
        systemMenu.setIcon(icon);
        systemMenu.setOrderNum(0);
        systemMenu.setCreateTime(new Date());
        systemMenu.setModifiedTime(new Date());
        return systemMenuService.addSysMenu(systemMenu);
    }

    /**
     * 获取菜单详细信息
     * @return
     */
    @RequestMapping("/getSysMenuInfo")
    @RequiresPermissions("sys:sysMenu:editMenu")
    public JsonResult getSysMenuInfo() {
        int id = getParams().getInt("id");
        return systemMenuService.getSysMenuInfo(id);
    }

    /**
     * 编辑菜单
     * @return
     */
    @RequestMapping("/editSysMenu")
    @RequiresPermissions("sys:sysMenu:editMenu")
    public JsonResult editSysMenu() {
        int id = getParams().getInt("id");
        int parentId = 0;
        if(getParams().containsKey("parentId")) {
            parentId = getParams().getInt("parentId");
        }
        int type = getParams().getInt("type");
        String name = getParams().getString("name");
        String url = null;
        if(getParams().containsKey("url")) {
            url = getParams().getString("url");
        }
        String perms = null;
        if(getParams().containsKey("perms")) {
            perms = getParams().getString("perms");
        }
        String icon = null;
        if(getParams().containsKey("icon")) {
            icon = getParams().getString("icon");
        }
        SystemMenu systemMenu = new SystemMenu();
        systemMenu.setId(id);
        systemMenu.setParentId(parentId);
        systemMenu.setName(name);
        systemMenu.setUrl(url);
        systemMenu.setPerms(perms);
        systemMenu.setType(type);
        systemMenu.setIcon(icon);
        systemMenu.setModifiedTime(new Date());
        return systemMenuService.editSysMenu(systemMenu);
    }

    /**
     * 删除菜单
     * @return
     */
    @RequestMapping("/deleteSysMenu")
    @RequiresPermissions("sys:sysMenu:deleteMenu")
    public JsonResult deleteSysMenu() {
        int id = getParams().getInt("id");
        return systemMenuService.deleteSysMenu(id);
    }


    /**
     * 获取用户菜单列表
     * @return
     */
    @RequestMapping("/getUserMenuList")
    public JsonResult getUserMenuList() {
        int id = getParams().getInt("id");
        return systemMenuService.getUserMenuList(id);
    }
}