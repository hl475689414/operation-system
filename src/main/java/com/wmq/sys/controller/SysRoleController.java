package com.wmq.sys.controller;

import com.wmq.sys.service.SystemRoleService;
import com.wmq.sys.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 李怀鹏 on 2018/7/11.
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {
    @Autowired
    private SystemRoleService systemRoleService;

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping("/getRoleList")
    @RequiresPermissions("sys:sysRole:roleList")
    public JsonResult getRoleList() {
        return systemRoleService.getRoleListPage();
    }

    /**
     * 新增角色
     * @return
     */
    @RequestMapping("/addRole")
    @RequiresPermissions("sys:sysRole:addRole")
    public JsonResult addRole() {
        String roleName = getParams().getString("roleName");
        int deptId = getParams().getInt("deptId");
        String remark = null;
        if(getParams().containsKey("remark")) {
            remark = getParams().getString("remark");
        }
        return systemRoleService.addRole(roleName, deptId, remark);
    }

    /**
     * 编辑角色
     * @return
     */
    @RequestMapping("/editRole")
    @RequiresPermissions("sys:sysRole:editRole")
    public JsonResult editRole() {
        int id = getParams().getInt("id");
        String roleName = getParams().getString("roleName");
        int deptId = getParams().getInt("deptId");
        String remark = getParams().getString("remark");
        return systemRoleService.editRole(id, roleName, deptId, remark);
    }

    /**
     * 获取角色详细信息
     * @return
     */
    @RequestMapping("/getRoleInfo")
    @RequiresPermissions("sys:sysRole:setupRole")
    public JsonResult getRoleInfo() {
        int id = getParams().getInt("id");
        return systemRoleService.getRoleInfo(id);
    }

    /**
     * 设置权限
     * @return
     */
    @RequestMapping("/setupRole")
    @RequiresPermissions("sys:sysRole:setupRole")
    public JsonResult setupRole() {
        int id = getParams().getInt("id");
        String roleMenus = getParams().getString("roleMenus");
        return systemRoleService.setupRole(id, roleMenus);
    }

    /**
     * 获取部门关联的角色
     * @return
     */
    @RequestMapping("/getDeptRole")
    public JsonResult getDeptRole() {
        int deptId = getParams().getInt("deptId");
        return systemRoleService.getDeptRole(deptId);
    }

    /**
     * 删除角色
     * @return
     */
    @RequestMapping("/deleteRole")
    @RequiresPermissions("sys:sysRole:deleteRole")
    public JsonResult deleteRole() {
        int id = getParams().getInt("id");
        return systemRoleService.deleteRole(id);
    }
}
