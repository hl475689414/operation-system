package com.wmq.sys.entity;

public class SystemDept {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer orderNum;

    private String remarks;

    private Integer delFlag;

    public SystemDept(Integer id, Integer parentId, String name, Integer orderNum, String remarks, Integer delFlag) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.orderNum = orderNum;
        this.remarks = remarks;
        this.delFlag = delFlag;
    }

    public SystemDept() {
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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}