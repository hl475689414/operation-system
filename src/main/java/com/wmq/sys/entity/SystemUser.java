package com.wmq.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class SystemUser implements Serializable {
    private Integer id;

    private String account;

    private String userName;

    private String passWord;

    private Integer deptId;

    private Integer roleId;

    private String mobile;

    private Integer status;

    private Integer userIdCreate;

    private Date createTime;

    private Date modifiedTime;

    private Integer delFlag;

    public SystemUser(Integer id, String account, String userName, String passWord, Integer deptId, Integer roleId, String mobile, Integer status, Integer userIdCreate, Date createTime, Date modifiedTime, Integer delFlag) {
        this.id = id;
        this.account = account;
        this.userName = userName;
        this.passWord = passWord;
        this.deptId = deptId;
        this.roleId = roleId;
        this.mobile = mobile;
        this.status = status;
        this.userIdCreate = userIdCreate;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.delFlag = delFlag;
    }

    public SystemUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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