package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/5/24.
 */
public class RevenueAmountOverviewVo {
    private double totalAmount; //营收总金额
    private double courseAmount; //订阅课程收入
    private String courseAmountProportion; //订阅课程收入百分比
    private double vipAmount; //新增企业Vip收入
    private String vipAmountProportion; //新增企业VIP收入百分比
    private double upgradeVipAmount; //企业升级VIP收入
    private String upgradeVipProportion; //企业升级VIP收入百分比

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getCourseAmount() {
        return courseAmount;
    }

    public void setCourseAmount(double courseAmount) {
        this.courseAmount = courseAmount;
    }

    public String getCourseAmountProportion() {
        return courseAmountProportion;
    }

    public void setCourseAmountProportion(String courseAmountProportion) {
        this.courseAmountProportion = courseAmountProportion;
    }

    public double getVipAmount() {
        return vipAmount;
    }

    public void setVipAmount(double vipAmount) {
        this.vipAmount = vipAmount;
    }

    public String getVipAmountProportion() {
        return vipAmountProportion;
    }

    public void setVipAmountProportion(String vipAmountProportion) {
        this.vipAmountProportion = vipAmountProportion;
    }

    public double getUpgradeVipAmount() {
        return upgradeVipAmount;
    }

    public void setUpgradeVipAmount(double upgradeVipAmount) {
        this.upgradeVipAmount = upgradeVipAmount;
    }

    public String getUpgradeVipProportion() {
        return upgradeVipProportion;
    }

    public void setUpgradeVipProportion(String upgradeVipProportion) {
        this.upgradeVipProportion = upgradeVipProportion;
    }
}
