package com.wmq.sys.vo;

import java.util.List;

/**
 * Created by 李怀鹏 on 2018/6/25.
 */
public class JobClassListVo {
    private int id;
    private String title;
    private List<JobClassChildrenVo> childrenList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<JobClassChildrenVo> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<JobClassChildrenVo> childrenList) {
        this.childrenList = childrenList;
    }
}
