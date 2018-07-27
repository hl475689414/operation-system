package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/6/23.
 */
public class PersonnelPushRecordListVo {
    private int id; //推送记录ID
    private String className; //招聘岗位名称
    private String company; //公司名称
    private String sysUserName; //操作人员
    private int recommendNum; //推荐数量

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public int getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(int recommendNum) {
        this.recommendNum = recommendNum;
    }
}
