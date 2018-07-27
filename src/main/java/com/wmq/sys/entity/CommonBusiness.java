package com.wmq.sys.entity;

public class CommonBusiness {
    private Integer id;

    private String name;

    private Integer parentid;

    private Integer sort;

    private Integer isdelete;

    public CommonBusiness(Integer id, String name, Integer parentid, Integer sort, Integer isdelete) {
        this.id = id;
        this.name = name;
        this.parentid = parentid;
        this.sort = sort;
        this.isdelete = isdelete;
    }

    public CommonBusiness() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}