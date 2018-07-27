package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/7/11.
 */
public interface SystemRoleService {
    /**
     * 获取角色列表
     * @return
     */
    JsonResult getRoleListPage();

    /**
     * 新增角色
     * @param roleName
     * @param deptId
     * @param remark
     * @return
     */
    JsonResult addRole(String roleName, int deptId, String remark);

    /**
     * 编辑角色
     * @param id
     * @param roleName
     * @param deptId
     * @param remark
     * @return
     */
    JsonResult editRole(int id, String roleName, int deptId, String remark);

    /**
     * 获取角色详细信息
     * @param id
     * @return
     */
    JsonResult getRoleInfo(int id);

    /**
     * 设置权限
     * @param id
     * @param roleMenus
     */
    JsonResult setupRole(int id, String roleMenus);

    /**
     * 获取部门关联的角色
     * @param deptId
     * @return
     */
    JsonResult getDeptRole(int deptId);

    /**
     * 删除角色
     * @param id
     * @return
     */
    JsonResult deleteRole(int id);
}
