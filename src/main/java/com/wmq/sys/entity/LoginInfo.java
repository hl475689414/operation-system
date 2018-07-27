package com.wmq.sys.entity;

import java.util.Date;

public class LoginInfo {
    private Integer id;

    private Integer userid;

    private String longitude;

    private String latitude;

    private String nettype;

    private String devicetype;

    private String deviceid;

    private String sysversion;

    private Date cratetime;

    private Integer loginout;

    private String ip;

    private String province;

    private String city;

    public LoginInfo(Integer id, Integer userid, String longitude, String latitude, String nettype, String devicetype, String deviceid, String sysversion, Date cratetime, Integer loginout, String ip, String province, String city) {
        this.id = id;
        this.userid = userid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nettype = nettype;
        this.devicetype = devicetype;
        this.deviceid = deviceid;
        this.sysversion = sysversion;
        this.cratetime = cratetime;
        this.loginout = loginout;
        this.ip = ip;
        this.province = province;
        this.city = city;
    }

    public LoginInfo() {
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getNettype() {
        return nettype;
    }

    public void setNettype(String nettype) {
        this.nettype = nettype == null ? null : nettype.trim();
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getSysversion() {
        return sysversion;
    }

    public void setSysversion(String sysversion) {
        this.sysversion = sysversion == null ? null : sysversion.trim();
    }

    public Date getCratetime() {
        return cratetime;
    }

    public void setCratetime(Date cratetime) {
        this.cratetime = cratetime;
    }

    public Integer getLoginout() {
        return loginout;
    }

    public void setLoginout(Integer loginout) {
        this.loginout = loginout;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
}