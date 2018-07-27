package com.wmq.sys.entity;

import java.util.Date;

public class CommonAuthOrder {
    private Integer id;

    private String subject;

    private Integer authtype;

    private Integer companyid;

    private Integer money;

    private Date ordertime;

    private Integer state;

    private String paytype;

    private Date paytime;

    private String discount;

    private Integer vipprice;

    private Integer type;

    public CommonAuthOrder(Integer id, String subject, Integer authtype, Integer companyid, Integer money, Date ordertime, Integer state, String paytype, Date paytime, String discount, Integer vipprice, Integer type) {
        this.id = id;
        this.subject = subject;
        this.authtype = authtype;
        this.companyid = companyid;
        this.money = money;
        this.ordertime = ordertime;
        this.state = state;
        this.paytype = paytype;
        this.paytime = paytime;
        this.discount = discount;
        this.vipprice = vipprice;
        this.type = type;
    }

    public CommonAuthOrder() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getAuthtype() {
        return authtype;
    }

    public void setAuthtype(Integer authtype) {
        this.authtype = authtype;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount == null ? null : discount.trim();
    }

    public Integer getVipprice() {
        return vipprice;
    }

    public void setVipprice(Integer vipprice) {
        this.vipprice = vipprice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}