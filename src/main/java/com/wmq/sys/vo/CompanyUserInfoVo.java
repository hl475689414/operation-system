package com.wmq.sys.vo;

import java.util.List;

/**
 * Created by 李怀鹏 on 2018/5/16.
 */
public class CompanyUserInfoVo {
    private int userId; //用户ID
    private int companyId; //企业ID
    private int authVip; //VIP等级
    private String fullName; //公司全称
    private String position; //我的职位
    private String title; //公司简称
    private String website; //公司官网
    private String address; //公司地址
    private String email; //邮箱
    private int business; //行业ID
    private String businessName; //行业名称
    private String jobVideoUrl; //视频秀地址
    private int staffNum; //员工人数
    private String starText; //团队亮点
    private String content; //公司简介
    private String senceUrl; //场景地址
    private List<RecruitJobBaseListVo> recruitJobBaseListVos; //职位列表

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getAuthVip() {
        return authVip;
    }

    public void setAuthVip(int authVip) {
        this.authVip = authVip;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBusiness() {
        return business;
    }

    public void setBusiness(int business) {
        this.business = business;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getJobVideoUrl() {
        return jobVideoUrl;
    }

    public void setJobVideoUrl(String jobVideoUrl) {
        this.jobVideoUrl = jobVideoUrl;
    }

    public int getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(int staffNum) {
        this.staffNum = staffNum;
    }

    public String getStarText() {
        return starText;
    }

    public void setStarText(String starText) {
        this.starText = starText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenceUrl() {
        return senceUrl;
    }

    public void setSenceUrl(String senceUrl) {
        this.senceUrl = senceUrl;
    }

    public List<RecruitJobBaseListVo> getRecruitJobBaseListVos() {
        return recruitJobBaseListVos;
    }

    public void setRecruitJobBaseListVos(List<RecruitJobBaseListVo> recruitJobBaseListVos) {
        this.recruitJobBaseListVos = recruitJobBaseListVos;
    }
}
