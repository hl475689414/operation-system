package com.wmq.sys.vo;

/**
 * Created by 程江涛 on 2018/5/17 0017
 */
public class HomeCountVO {
    private String day;

    private Integer count;

    public HomeCountVO() {
    }

    public HomeCountVO(String day, Integer count) {
        this.day = day;
        this.count = count;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "HomeCountVO{" +
                "day='" + day + '\'' +
                ", count=" + count +
                '}';
    }
}
