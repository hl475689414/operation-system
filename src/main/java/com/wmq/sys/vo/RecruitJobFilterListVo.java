package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/5/15.
 */
public class RecruitJobFilterListVo {
    private int id; //求职意向ID
    private int classId; //职位ID
    private String className; //职位名称
    private int businessId; //行业ID
    private String businessName; //行业类型
    private int cityId; //城市ID
    private String cityName; //城市名称
    private int workPayStart; //开始薪资
    private int workPayEnd; //结束薪资

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getWorkPayStart() {
        return workPayStart;
    }

    public void setWorkPayStart(int workPayStart) {
        this.workPayStart = workPayStart;
    }

    public int getWorkPayEnd() {
        return workPayEnd;
    }

    public void setWorkPayEnd(int workPayEnd) {
        this.workPayEnd = workPayEnd;
    }
}
