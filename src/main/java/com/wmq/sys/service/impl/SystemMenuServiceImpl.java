package com.wmq.sys.service.impl;

import com.wmq.sys.dao.SystemMenuMapper;
import com.wmq.sys.dao.SystemRoleMenuMapper;
import com.wmq.sys.entity.SystemMenu;
import com.wmq.sys.service.SystemMenuService;
import com.wmq.sys.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by 李怀鹏 on 2018/6/14.
 */
@Service("sysMenuService")
public class SystemMenuServiceImpl extends BaseService implements SystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;
    @Autowired
    private SystemRoleMenuMapper systemRoleMenuMapper;

    /**
     * 获取用户角色权限列表
     * @param userId
     * @return
     */
    @Override
    public Set<String> listPerms(int userId) {
        List<String> perms = systemMenuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 获取平台菜单列表
     * @return
     */
    @Override
    public JsonResult getSysMenuList() {
        List<SystemMenu> listmenu = systemMenuMapper.getAllMenuList();
        listAllTree = new ArrayList<>();
        List<Object> list = generateAllMenuTree(listmenu);
        return new JsonResult(0, "获取成功", list.size(), list);
    }

    /**
     * 获取所有一级菜单
     */
    public List<Object> listAllTree = null; //最终树状结构结果集
    public List<Object> generateAllMenuTree(List<SystemMenu> menuList) {
        for (SystemMenu systemMenu : menuList) {
            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
            if(systemMenu.getParentId() == 0){
                mapArr.put("id", systemMenu.getId());
                mapArr.put("parentId", systemMenu.getParentId());
                mapArr.put("name", systemMenu.getName());
                mapArr.put("url", systemMenu.getUrl());
                mapArr.put("perms", systemMenu.getPerms());
                mapArr.put("type", systemMenu.getType());
                if(allMenuChild(systemMenu.getId(), menuList).size() > 0) {
                    mapArr.put("childList", allMenuChild(systemMenu.getId(), menuList));
                }
                listAllTree.add(mapArr);
            }
        }
        return listAllTree;
    }

    /**
     * 获取所有一级菜单下的所有菜单
     * @param id
     * @param menuList
     * @return
     */
    public List<?> allMenuChild(int id, List<SystemMenu> menuList){
        List<Object> lists = new ArrayList<Object>();
        for(SystemMenu systemMenu : menuList){
            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
            if(systemMenu.getParentId() == id){
                childArray.put("id", systemMenu.getId());
                childArray.put("parentId", systemMenu.getParentId());
                childArray.put("name", systemMenu.getName());
                childArray.put("url", systemMenu.getUrl());
                childArray.put("perms", systemMenu.getPerms());
                childArray.put("type", systemMenu.getType());
                if(menuChild(systemMenu.getId(), menuList).size() > 0) {
                    childArray.put("childList", allMenuChild(systemMenu.getId(), menuList));
                }
                lists.add(childArray);
            }
        }
        return lists;
    }

    /**
     * 获取树状结构菜单
     * @return
     */
    @Override
    public List<Object> getMenuTree() {
        listTree = new ArrayList<>();
        //所有菜单
        List<SystemMenu> menuList = systemMenuMapper.getMenuList();
        return generateMenuTree(menuList);
    }

    /**
     * 获取所有一级菜单
     */
    public List<Object> listTree = null; //最终树状结构结果集
    public List<Object> generateMenuTree(List<SystemMenu> menuList) {
        for (SystemMenu systemMenu : menuList) {
            Map<String,Object> mapArr = new LinkedHashMap<String, Object>();
            if(systemMenu.getParentId() == 0){
                mapArr.put("id", systemMenu.getId());
                mapArr.put("name", systemMenu.getName());
                if(menuChild(systemMenu.getId(), menuList).size() > 0) {
                    mapArr.put("childList", menuChild(systemMenu.getId(), menuList));
                }
                listTree.add(mapArr);
            }
        }
        return listTree;
    }

    /**
     * 获取所有一级菜单下的所有菜单
     * @param id
     * @param menuList
     * @return
     */
    public List<?> menuChild(int id, List<SystemMenu> menuList){
        List<Object> lists = new ArrayList<Object>();
        for(SystemMenu systemMenu : menuList){
            Map<String,Object> childArray = new LinkedHashMap<String, Object>();
            if(systemMenu.getParentId() == id){
                childArray.put("id", systemMenu.getId());
                childArray.put("name", systemMenu.getName());
                if(menuChild(systemMenu.getId(), menuList).size() > 0) {
                    childArray.put("childList", menuChild(systemMenu.getId(), menuList));
                }
                lists.add(childArray);
            }
        }
        return lists;
    }

    /**
     * 新增菜单
     * @param systemMenu
     * @return
     */
    @Override
    public JsonResult addSysMenu(SystemMenu systemMenu) {
        systemMenuMapper.insertSelective(systemMenu);
        return new JsonResult(0, "新增成功");
    }

    /**
     * 获取菜单详细信息
     * @param id
     * @return
     */
    @Override
    public JsonResult getSysMenuInfo(int id) {
        SystemMenu systemMenu = systemMenuMapper.selectByPrimaryKey(id);
        return new JsonResult(0, "获取成功", 0, systemMenu);
    }

    /**
     * 编辑菜单
     * @param systemMenu
     * @return
     */
    @Override
    public JsonResult editSysMenu(SystemMenu systemMenu) {
        systemMenuMapper.updateByPrimaryKeySelective(systemMenu);
        return new JsonResult(0, "编辑成功");
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public JsonResult deleteSysMenu(int id) {
        SystemMenu systemMenu = systemMenuMapper.selectByPrimaryKey(id);
        if(empty(systemMenu)) {
            return new JsonResult(1, "菜单不存在");
        }
        List<SystemMenu> list = systemMenuMapper.getMenuList();
        listMenuId = new ArrayList<>();
        listMenuId.add(id);
        //获取所有要删除的菜单ID
        listMenuId = currentMenuAllId(id, list);
        //批量删除菜单角色关联数据
        systemRoleMenuMapper.deleteByRoleMenuList(listMenuId);
        //批量删除菜单
        systemMenuMapper.deleteByListId(listMenuId);
        return new JsonResult(0, "删除成功");
    }

    /**
     * 获取当前菜单下所有菜单ID
     * @param currentMenuId 当前菜单ID
     * @return
     */
    private List<Integer> listMenuId = null; //所有要删除的菜单ID
    public List<Integer> currentMenuAllId(int currentMenuId, List<SystemMenu> list) {
        List<SystemMenu> menus = new ArrayList<>();
        for(SystemMenu systemMenu : list) {
            if(systemMenu.getParentId() == currentMenuId) {
                menus.add(systemMenu);
            }
        }
        for (SystemMenu menu : menus) {
            listMenuId.add(menu.getId());
            currentMenuAllId(menu.getId(), list);
        }
        return listMenuId;
    }
}
