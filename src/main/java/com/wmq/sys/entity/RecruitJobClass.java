package com.wmq.sys.entity;

public class RecruitJobClass {
    private Integer id;

    private String title;

    private Integer sort;

    private Integer groups;

    private Integer isdelete;

    public RecruitJobClass(Integer id, String title, Integer sort, Integer groups, Integer isdelete) {
        this.id = id;
        this.title = title;
        this.sort = sort;
        this.groups = groups;
        this.isdelete = isdelete;
    }

    public RecruitJobClass() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}