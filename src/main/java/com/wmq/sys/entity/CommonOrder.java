package com.wmq.sys.entity;

import java.util.Date;

public class CommonOrder {
    private Integer id;

    private Integer userid;

    private Integer touserid;

    private Integer shareuserid;

    private Integer money;

    private Integer shoping;

    private Integer shopingtype;

    private Integer paytype;

    private Integer state;

    private Date createtime;

    private Date updatetime;

    private String outherinfo;

    public CommonOrder(Integer id, Integer userid, Integer touserid, Integer shareuserid, Integer money, Integer shoping, Integer shopingtype, Integer paytype, Integer state, Date createtime, Date updatetime, String outherinfo) {
        this.id = id;
        this.userid = userid;
        this.touserid = touserid;
        this.shareuserid = shareuserid;
        this.money = money;
        this.shoping = shoping;
        this.shopingtype = shopingtype;
        this.paytype = paytype;
        this.state = state;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.outherinfo = outherinfo;
    }

    public CommonOrder() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTouserid() {
        return touserid;
    }

    public void setTouserid(Integer touserid) {
        this.touserid = touserid;
    }

    public Integer getShareuserid() {
        return shareuserid;
    }

    public void setShareuserid(Integer shareuserid) {
        this.shareuserid = shareuserid;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getShoping() {
        return shoping;
    }

    public void setShoping(Integer shoping) {
        this.shoping = shoping;
    }

    public Integer getShopingtype() {
        return shopingtype;
    }

    public void setShopingtype(Integer shopingtype) {
        this.shopingtype = shopingtype;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getOutherinfo() {
        return outherinfo;
    }

    public void setOutherinfo(String outherinfo) {
        this.outherinfo = outherinfo == null ? null : outherinfo.trim();
    }
}