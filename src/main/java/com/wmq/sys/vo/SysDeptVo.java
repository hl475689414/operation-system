package com.wmq.sys.vo;

import java.io.Serializable;

/**
 * Created by 李怀鹏 on 2018/7/9.
 */
public class SysDeptVo implements Serializable {
    private int id; //部门ID
    private int parentId; //父级ID
    private String name; //部门名称
    private String parentName; //上级部门名称
    private String remarks; //备注

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
