package com.wmq.sys.entity;

import java.util.Date;

public class SystemRole {
    private Integer id;

    private String roleName;

    private String roleSign;

    private String remark;

    private Integer deptId;

    private Integer userIdCreate;

    private Date createTime;

    private Date modifiedTime;

    private Integer delFlag;

    public SystemRole(Integer id, String roleName, String roleSign, String remark, Integer deptId, Integer userIdCreate, Date createTime, Date modifiedTime, Integer delFlag) {
        this.id = id;
        this.roleName = roleName;
        this.roleSign = roleSign;
        this.remark = remark;
        this.deptId = deptId;
        this.userIdCreate = userIdCreate;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.delFlag = delFlag;
    }

    public SystemRole() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleSign() {
        return roleSign;
    }

    public void setRoleSign(String roleSign) {
        this.roleSign = roleSign == null ? null : roleSign.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}