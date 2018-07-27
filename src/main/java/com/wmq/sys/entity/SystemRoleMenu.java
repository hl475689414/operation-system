package com.wmq.sys.entity;

public class SystemRoleMenu {
    private Integer id;

    private Integer roleId;

    private Integer menuId;

    public SystemRoleMenu(Integer id, Integer roleId, Integer menuId) {
        this.id = id;
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public SystemRoleMenu() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}