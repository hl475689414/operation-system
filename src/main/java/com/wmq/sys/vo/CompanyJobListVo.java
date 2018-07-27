package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/6/22.
 */
public class CompanyJobListVo {
    private int jobId; //招聘职位ID
    private int classId; //职位ID
    private String className; //职位名
    private int cityId; //城市ID
    private String city; //城市名称
    private String workYear; //经验需求
    private int vip; //VIP等级
    private int companyUserId; //企业用户ID
    private int companyId; //公司ID
    private String company; //公司名称
    private int recommendNum; //已推送人才数

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(int companyUserId) {
        this.companyUserId = companyUserId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(int recommendNum) {
        this.recommendNum = recommendNum;
    }
}
