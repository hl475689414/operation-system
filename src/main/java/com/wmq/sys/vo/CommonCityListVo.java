package com.wmq.sys.vo;

import java.util.List;

/**
 * Created by 李怀鹏 on 2018/6/25.
 */
public class CommonCityListVo {
    private int id;
    private String title;
    private List<CommonCityChildrenVo> childrenList;

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

    public List<CommonCityChildrenVo> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<CommonCityChildrenVo> childrenList) {
        this.childrenList = childrenList;
    }
}
