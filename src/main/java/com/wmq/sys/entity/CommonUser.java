package com.wmq.sys.entity;

import java.util.Date;

public class CommonUser {
    private Integer id;

    private String username;

    private String password;

    private String face;

    private String companyface;

    private String email;

    private String tel;

    private String truename;

    private Integer companyid;

    private String position;

    private Integer role;

    private String thirdauth;

    private String logindevice;

    private Integer companycity;

    private Integer city;

    private Integer gender;

    private String weixin;

    private String companyweixin;

    private String deviceid;

    private Date registertime;

    private Date logintime;

    private Integer state;

    private Integer isdelete;

    private Integer runday;

    private Integer fromtype;

    private String logindevicebrand;

    private String qq;

    public CommonUser(Integer id, String username, String password, String face, String companyface, String email, String tel, String truename, Integer companyid, String position, Integer role, String thirdauth, String logindevice, Integer companycity, Integer city, Integer gender, String weixin, String companyweixin, String deviceid, Date registertime, Date logintime, Integer state, Integer isdelete, Integer runday, Integer fromtype, String logindevicebrand, String qq) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.face = face;
        this.companyface = companyface;
        this.email = email;
        this.tel = tel;
        this.truename = truename;
        this.companyid = companyid;
        this.position = position;
        this.role = role;
        this.thirdauth = thirdauth;
        this.logindevice = logindevice;
        this.companycity = companycity;
        this.city = city;
        this.gender = gender;
        this.weixin = weixin;
        this.companyweixin = companyweixin;
        this.deviceid = deviceid;
        this.registertime = registertime;
        this.logintime = logintime;
        this.state = state;
        this.isdelete = isdelete;
        this.runday = runday;
        this.fromtype = fromtype;
        this.logindevicebrand = logindevicebrand;
        this.qq = qq;
    }

    public CommonUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face == null ? null : face.trim();
    }

    public String getCompanyface() {
        return companyface;
    }

    public void setCompanyface(String companyface) {
        this.companyface = companyface == null ? null : companyface.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getThirdauth() {
        return thirdauth;
    }

    public void setThirdauth(String thirdauth) {
        this.thirdauth = thirdauth == null ? null : thirdauth.trim();
    }

    public String getLogindevice() {
        return logindevice;
    }

    public void setLogindevice(String logindevice) {
        this.logindevice = logindevice == null ? null : logindevice.trim();
    }

    public Integer getCompanycity() {
        return companycity;
    }

    public void setCompanycity(Integer companycity) {
        this.companycity = companycity;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getCompanyweixin() {
        return companyweixin;
    }

    public void setCompanyweixin(String companyweixin) {
        this.companyweixin = companyweixin == null ? null : companyweixin.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getRunday() {
        return runday;
    }

    public void setRunday(Integer runday) {
        this.runday = runday;
    }

    public Integer getFromtype() {
        return fromtype;
    }

    public void setFromtype(Integer fromtype) {
        this.fromtype = fromtype;
    }

    public String getLogindevicebrand() {
        return logindevicebrand;
    }

    public void setLogindevicebrand(String logindevicebrand) {
        this.logindevicebrand = logindevicebrand == null ? null : logindevicebrand.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }
}