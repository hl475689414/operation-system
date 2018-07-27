package com.wmq.sys.service;

import com.wmq.sys.entity.SystemMenu;
import com.wmq.sys.utils.JsonResult;

import java.util.List;
import java.util.Set;

/**
 * Created by 李怀鹏 on 2018/6/14.
 */
public interface SystemMenuService {
    /**
     * 获取用户角色权限列表
     * @param userId
     * @return
     */
    Set<String> listPerms(int userId);

    /**
     * 获取平台菜单列表
     * @return
     */
    JsonResult getSysMenuList();

    /**
     * 获取树状结构菜单列表
     * @return
     */
    List<Object> getMenuTree();

    /**
     * 新增菜单
     * @param systemMenu
     * @return
     */
    JsonResult addSysMenu(SystemMenu systemMenu);

    /**
     * 获取菜单详细信息
     * @param id
     * @return
     */
    JsonResult getSysMenuInfo(int id);

    /**
     * 编辑菜单
     * @param systemMenu
     * @return
     */
    JsonResult editSysMenu(SystemMenu systemMenu);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    JsonResult deleteSysMenu(int id);
}
