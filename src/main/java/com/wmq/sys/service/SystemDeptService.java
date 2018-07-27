package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/7/9.
 */
public interface SystemDeptService {
    /**
     * 获取部门列表
     * @return
     */
    JsonResult getDeptListPage();

    /**
     * 新增部门
     * @param name
     * @param parentId
     * @param remarks
     * @return
     */
    JsonResult addDept(String name, int parentId, String remarks);

    /**
     * 编辑部门
     * @param id
     * @param name
     * @param parentId
     * @param remarks
     * @return
     */
    JsonResult editDept(int id, String name, int parentId, String remarks);

    /**
     * 删除部门
     * @param id
     * @return
     */
    JsonResult deleteDept(int id);

    /**
     * 获取所有部门
     * @return
     */
    JsonResult getAllDeptList();
}
