package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/7/19.
 */
public class CommonAuthOrderInfoVo {
    private int id; //订单号
    private String state; //支付状态
    private String buyerName; //买家姓名
    private String payType; //支付方式
    private String buyerMobile; //买家电话
    private String orderTime; //下单时间
    private String subject; //商品名称
    private Double money; //金额
    private String currency; //币种
    private String businessPreferences; //商家优惠
    private Double businessTetReceipts; //商家实收
    private String ServiceCharge; //服务费
    private String refundMoeny; //退款金额
    private Double NetReceiptsAmount; //实收净额
    private String commodityDescribe; //商品描述

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBusinessPreferences() {
        return businessPreferences;
    }

    public void setBusinessPreferences(String businessPreferences) {
        this.businessPreferences = businessPreferences;
    }

    public Double getBusinessTetReceipts() {
        return businessTetReceipts;
    }

    public void setBusinessTetReceipts(Double businessTetReceipts) {
        this.businessTetReceipts = businessTetReceipts;
    }

    public String getServiceCharge() {
        return ServiceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        ServiceCharge = serviceCharge;
    }

    public String getRefundMoeny() {
        return refundMoeny;
    }

    public void setRefundMoeny(String refundMoeny) {
        this.refundMoeny = refundMoeny;
    }

    public Double getNetReceiptsAmount() {
        return NetReceiptsAmount;
    }

    public void setNetReceiptsAmount(Double netReceiptsAmount) {
        NetReceiptsAmount = netReceiptsAmount;
    }

    public String getCommodityDescribe() {
        return commodityDescribe;
    }

    public void setCommodityDescribe(String commodityDescribe) {
        this.commodityDescribe = commodityDescribe;
    }
}
