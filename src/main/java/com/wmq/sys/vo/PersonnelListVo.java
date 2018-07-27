package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/6/22.
 */
public class PersonnelListVo {
    private int filterId; //求职意向ID
    private int classId; //意向类型ID
    private String className; //意向类型名称
    private int userId; //用户ID
    private String trueName; //用户姓名
    private String mobile; //电话
    private int businessId; //行业ID
    private String business; //行业类型
    private int cityId; //城市ID
    private String city; //城市名称
    private String workYear; //工作经验
    private String loginTime; //上次登录时间
    private int recommendNum; //推送次数
    private int entryState; //简历状态
    private String ascriptionUser; //归属人
    private String passWord; //环信密码

    public int getFilterId() {
        return filterId;
    }

    public void setFilterId(int filterId) {
        this.filterId = filterId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
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

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(int recommendNum) {
        this.recommendNum = recommendNum;
    }

    public int getEntryState() {
        return entryState;
    }

    public void setEntryState(int entryState) {
        this.entryState = entryState;
    }

    public String getAscriptionUser() {
        return ascriptionUser;
    }

    public void setAscriptionUser(String ascriptionUser) {
        this.ascriptionUser = ascriptionUser;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
