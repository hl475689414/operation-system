package com.wmq.sys.service.impl;

import com.wmq.sys.dao.SystemRoleMapper;
import com.wmq.sys.dao.SystemRoleMenuMapper;
import com.wmq.sys.dao.SystemUserMapper;
import com.wmq.sys.entity.SystemRole;
import com.wmq.sys.entity.SystemRoleMenu;
import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.service.SystemMenuService;
import com.wmq.sys.service.SystemRoleService;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.vo.SysRoleListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 李怀鹏 on 2018/7/11.
 */
@Service("sysRoleService")
public class SystemRoleServiceImpl extends BaseService implements SystemRoleService {
    @Autowired
    private SystemRoleMapper systemRoleMapper;
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemRoleMenuMapper systemRoleMenuMapper;
    @Autowired
    private SystemMenuService systemMenuService;

    /**
     * 获取角色列表
     * @return
     */
    @Override
    public JsonResult getRoleListPage() {
        List<SysRoleListVo> list = systemRoleMapper.getRoleListPage();
        return new JsonResult(0, "获取成功", getTotalCount(), list);
    }

    /**
     * 新增角色
     * @param roleName
     * @param deptId
     * @param remark
     * @return
     */
    @Override
    public JsonResult addRole(String roleName, int deptId, String remark) {
        SystemRole systemRole = new SystemRole();
        systemRole.setRoleName(roleName);
        systemRole.setDeptId(deptId);
        systemRole.setRemark(remark);
        SystemUser systemUser = super.getSysUser();
        systemRole.setUserIdCreate(systemUser.getId());
        systemRole.setCreateTime(new Date());
        systemRole.setModifiedTime(new Date());
        systemRoleMapper.insertSelective(systemRole);
        return new JsonResult(0, "新增成功");
    }

    /**
     * 编辑角色
     * @param id
     * @param roleName
     * @param deptId
     * @param remark
     * @return
     */
    @Override
    public JsonResult editRole(int id, String roleName, int deptId, String remark) {
        SystemRole systemRole = systemRoleMapper.selectByPrimaryKey(id);
        if(empty(systemRole)) {
            return new JsonResult(1, "角色不存在");
        }
        if(systemRole.getDeptId() > 0 && deptId != systemRole.getDeptId()) {
            return new JsonResult(1, "请先删除角色所属部门再更换部门");
        }
        systemRole.setRoleName(roleName);
        systemRole.setDeptId(deptId);
        systemRole.setRemark(remark);
        systemRole.setModifiedTime(new Date());
        systemRoleMapper.updateByPrimaryKeySelective(systemRole);
        return new JsonResult(0, "编辑成功");
    }

    /**
     * 获取角色详细信息
     * @param id
     * @return
     */
    @Override
    public JsonResult getRoleInfo(int id) {
        Map<String, Object> map = new HashMap<>();
        //所属部门
        String deptName = systemRoleMapper.getRoleDeptName(id);
        map.put("deptName",deptName);
        //拥有此角色的用户
        List<String> userList = systemUserMapper.getRoleUserList(id);
        String roleUserStr = "";
        for(String str : userList) {
            roleUserStr += str + ",";
        }
        map.put("roleUserList",userList.size() > 0 ? roleUserStr.substring(0,roleUserStr.length() - 1) : roleUserStr);
        map.put("menuAllList",systemMenuService.getMenuTree());
        //当前角色所拥有菜单
        List<Integer> choiceList = systemRoleMenuMapper.getRoleChoiceList(id);
        map.put("menuChoiceList",choiceList);
        return new JsonResult(0, "获取成功", 0, map);
    }

    /**
     * 设置权限
     * @param id
     * @param roleMenus
     */
    @Override
    public JsonResult setupRole(int id, String roleMenus) {
        //删除旧权限
        systemRoleMenuMapper.deleteByPrimaryRoleId(id);
        //批量插入新权限
        String[] menus = roleMenus.split(",");
        List<SystemRoleMenu> list = new ArrayList<>();
        SystemRoleMenu systemRoleMenu = null;
        for(String str : menus) {
            systemRoleMenu = new SystemRoleMenu();
            systemRoleMenu.setRoleId(id);
            systemRoleMenu.setMenuId(Integer.parseInt(str));
            list.add(systemRoleMenu);
        }
        systemRoleMenuMapper.insertRoleMenus(list);
        return new JsonResult(0, "设置成功");
    }

    /**
     * 获取部门关联的角色
     * @param deptId
     * @return
     */
    @Override
    public JsonResult getDeptRole(int deptId) {
        List<SystemRole> list = systemRoleMapper.getDeptRoleList(deptId);
        return new JsonResult(0, "获取成功", list.size(), list);
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteRole(int id) {
        SystemRole systemRole = systemRoleMapper.selectByPrimaryKey(id);
        if(empty(systemRole)) {
            return new JsonResult(1, "角色不存在");
        }
        //修改用户角色信息
        systemUserMapper.updateByRoleId(id);
        //删除角色关联的菜单
        systemRoleMenuMapper.deleteByPrimaryRoleId(id);
        //删除角色
        systemRoleMapper.deleteByPrimaryKey(id);
        return new JsonResult(0, "删除成功");
    }
}
