package com.wmq.sys.vo;

public class UserTrendOverviewVo {
    private int userTotal; //用户总数
    private int companyTotal; //入驻企业数
    private int personalTotal; //个人用户数
    private int todayAddCompanyTotal; //今日入驻企业数
    private int todayAddUserTotal; //今日新增用户数
    private int todayStartTotal; //今日启动数

    public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int userTotal) {
        this.userTotal = userTotal;
    }

    public int getCompanyTotal() {
        return companyTotal;
    }

    public void setCompanyTotal(int companyTotal) {
        this.companyTotal = companyTotal;
    }

    public int getPersonalTotal() {
        return personalTotal;
    }

    public void setPersonalTotal(int personalTotal) {
        this.personalTotal = personalTotal;
    }

    public int getTodayAddCompanyTotal() {
        return todayAddCompanyTotal;
    }

    public void setTodayAddCompanyTotal(int todayAddCompanyTotal) {
        this.todayAddCompanyTotal = todayAddCompanyTotal;
    }

    public int getTodayAddUserTotal() {
        return todayAddUserTotal;
    }

    public void setTodayAddUserTotal(int todayAddUserTotal) {
        this.todayAddUserTotal = todayAddUserTotal;
    }

    public int getTodayStartTotal() {
        return todayStartTotal;
    }

    public void setTodayStartTotal(int todayStartTotal) {
        this.todayStartTotal = todayStartTotal;
    }
}
