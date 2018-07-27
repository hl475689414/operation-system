package com.wmq.sys.entity;

import java.util.Date;

public class SystemMenu {
    private Integer id;

    private Integer parentId;

    private String name;

    private String url;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private Date createTime;

    private Date modifiedTime;

    public SystemMenu(Integer id, Integer parentId, String name, String url, String perms, Integer type, String icon, Integer orderNum, Date createTime, Date modifiedTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.perms = perms;
        this.type = type;
        this.icon = icon;
        this.orderNum = orderNum;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    public SystemMenu() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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
}