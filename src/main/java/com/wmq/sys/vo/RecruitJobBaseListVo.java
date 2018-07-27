package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/5/16.
 */
public class RecruitJobBaseListVo {
    private int id; //职位ID
    private int classId; //职位类型ID
    private String className; //职位类型名称
    private int displayState; //状态
    private String title; //职位名称
    private int business; //行业ID
    private String businessTitle; //行业名称
    private int payStart; //开始薪资
    private int payEndp; //结束薪资
    private int workYear; //工作年限
    private String workYearTitle; //工作年限文本
    private int education; //学历
    private String educationTitle; //学历文本
    private int cityId; //城市ID
    private String cityName; //城市名称

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

    public int getDisplayState() {
        return displayState;
    }

    public void setDisplayState(int displayState) {
        this.displayState = displayState;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBusiness() {
        return business;
    }

    public void setBusiness(int business) {
        this.business = business;
    }

    public String getBusinessTitle() {
        return businessTitle;
    }

    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle;
    }

    public int getPayStart() {
        return payStart;
    }

    public void setPayStart(int payStart) {
        this.payStart = payStart;
    }

    public int getPayEndp() {
        return payEndp;
    }

    public void setPayEndp(int payEndp) {
        this.payEndp = payEndp;
    }

    public int getWorkYear() {
        return workYear;
    }

    public void setWorkYear(int workYear) {
        this.workYear = workYear;
    }

    public String getWorkYearTitle() {
        return workYearTitle;
    }

    public void setWorkYearTitle(String workYearTitle) {
        this.workYearTitle = workYearTitle;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public String getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(String educationTitle) {
        this.educationTitle = educationTitle;
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
}
