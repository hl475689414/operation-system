package com.wmq.sys.controller;

import com.wmq.sys.service.SystemDeptService;
import com.wmq.sys.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 李怀鹏 on 2018/7/9.
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private SystemDeptService systemDeptService;

    /**
     * 获取部门列表
     * @return
     */
    @RequestMapping("/getDeptListPage")
    @RequiresPermissions("sys:sysDept:deptList")
    public JsonResult getDeptList() {
        return systemDeptService.getDeptListPage();
    }

    /**
     * 新增部门
     * @return
     */
    @RequestMapping("/addDept")
    @RequiresPermissions("sys:sysDept:add")
    public JsonResult addDept() {
        String name = getParams().getString("name");
        int parentId = getParams().getInt("parentId");
        String remarks = null;
        if(getParams().containsKey("remarks")) {
            remarks = getParams().getString("remarks");
        }
        return systemDeptService.addDept(name, parentId, remarks);
    }

    /**
     * 编辑部门
     * @return
     */
    @RequestMapping("/editDept")
    @RequiresPermissions("sys:sysDept:edit")
    public JsonResult editDept() {
        int id = getParams().getInt("id");
        String name = getParams().getString("name");
        int parentId = getParams().getInt("parentId");
        String remarks = null;
        if(getParams().containsKey("remarks")) {
            remarks = getParams().getString("remarks");
        }
        return systemDeptService.editDept(id, name, parentId, remarks);
    }

    /**
     * 删除部门
     * @return
     */
    @RequestMapping("/deleteDept")
    @RequiresPermissions("sys:sysDept:delete")
    public JsonResult deleteDept() {
        int id = getParams().getInt("id");
        return systemDeptService.deleteDept(id);
    }

    /**
     * 获取所有部门
     * @return
     */
    @RequestMapping("/getAllDeptList")
    public JsonResult getAllDeptList() {
        return systemDeptService.getAllDeptList();
    }
}
