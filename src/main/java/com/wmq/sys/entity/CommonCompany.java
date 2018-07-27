package com.wmq.sys.entity;

import java.util.Date;

public class CommonCompany {
    private Integer id;

    private String logo;

    private String title;

    private String fullname;

    private String website;

    private String address;

    private String business;

    private String sceneurl;

    private Integer staffnum;

    private String startext;

    private Integer city;

    private Integer phase;

    private Integer finance;

    private String slogan;

    private String content;

    private String longitude;

    private String latitude;

    private Integer masteruserid;

    private String workimg;

    private String licenseimg;

    private Integer authstate;

    private Date createtime;

    private Integer successtalktimes;

    private Integer talktimes;

    private Integer isdelete;

    private Date logintime;

    private Integer fullrate;

    private Integer authorder;

    private Integer authvip;

    private Date opentime;

    private Date expiretime;

    private String developmentresources;

    private Integer managementtype;

    private Integer professiontype;

    private Integer setuptime;

    public CommonCompany(Integer id, String logo, String title, String fullname, String website, String address, String business, String sceneurl, Integer staffnum, String startext, Integer city, Integer phase, Integer finance, String slogan, String content, String longitude, String latitude, Integer masteruserid, String workimg, String licenseimg, Integer authstate, Date createtime, Integer successtalktimes, Integer talktimes, Integer isdelete, Date logintime, Integer fullrate, Integer authorder, Integer authvip, Date opentime, Date expiretime, String developmentresources, Integer managementtype, Integer professiontype, Integer setuptime) {
        this.id = id;
        this.logo = logo;
        this.title = title;
        this.fullname = fullname;
        this.website = website;
        this.address = address;
        this.business = business;
        this.sceneurl = sceneurl;
        this.staffnum = staffnum;
        this.startext = startext;
        this.city = city;
        this.phase = phase;
        this.finance = finance;
        this.slogan = slogan;
        this.content = content;
        this.longitude = longitude;
        this.latitude = latitude;
        this.masteruserid = masteruserid;
        this.workimg = workimg;
        this.licenseimg = licenseimg;
        this.authstate = authstate;
        this.createtime = createtime;
        this.successtalktimes = successtalktimes;
        this.talktimes = talktimes;
        this.isdelete = isdelete;
        this.logintime = logintime;
        this.fullrate = fullrate;
        this.authorder = authorder;
        this.authvip = authvip;
        this.opentime = opentime;
        this.expiretime = expiretime;
        this.developmentresources = developmentresources;
        this.managementtype = managementtype;
        this.professiontype = professiontype;
        this.setuptime = setuptime;
    }

    public CommonCompany() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getSceneurl() {
        return sceneurl;
    }

    public void setSceneurl(String sceneurl) {
        this.sceneurl = sceneurl == null ? null : sceneurl.trim();
    }

    public Integer getStaffnum() {
        return staffnum;
    }

    public void setStaffnum(Integer staffnum) {
        this.staffnum = staffnum;
    }

    public String getStartext() {
        return startext;
    }

    public void setStartext(String startext) {
        this.startext = startext == null ? null : startext.trim();
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Integer getFinance() {
        return finance;
    }

    public void setFinance(Integer finance) {
        this.finance = finance;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan == null ? null : slogan.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Integer getMasteruserid() {
        return masteruserid;
    }

    public void setMasteruserid(Integer masteruserid) {
        this.masteruserid = masteruserid;
    }

    public String getWorkimg() {
        return workimg;
    }

    public void setWorkimg(String workimg) {
        this.workimg = workimg == null ? null : workimg.trim();
    }

    public String getLicenseimg() {
        return licenseimg;
    }

    public void setLicenseimg(String licenseimg) {
        this.licenseimg = licenseimg == null ? null : licenseimg.trim();
    }

    public Integer getAuthstate() {
        return authstate;
    }

    public void setAuthstate(Integer authstate) {
        this.authstate = authstate;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getSuccesstalktimes() {
        return successtalktimes;
    }

    public void setSuccesstalktimes(Integer successtalktimes) {
        this.successtalktimes = successtalktimes;
    }

    public Integer getTalktimes() {
        return talktimes;
    }

    public void setTalktimes(Integer talktimes) {
        this.talktimes = talktimes;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Integer getFullrate() {
        return fullrate;
    }

    public void setFullrate(Integer fullrate) {
        this.fullrate = fullrate;
    }

    public Integer getAuthorder() {
        return authorder;
    }

    public void setAuthorder(Integer authorder) {
        this.authorder = authorder;
    }

    public Integer getAuthvip() {
        return authvip;
    }

    public void setAuthvip(Integer authvip) {
        this.authvip = authvip;
    }

    public Date getOpentime() {
        return opentime;
    }

    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public String getDevelopmentresources() {
        return developmentresources;
    }

    public void setDevelopmentresources(String developmentresources) {
        this.developmentresources = developmentresources == null ? null : developmentresources.trim();
    }

    public Integer getManagementtype() {
        return managementtype;
    }

    public void setManagementtype(Integer managementtype) {
        this.managementtype = managementtype;
    }

    public Integer getProfessiontype() {
        return professiontype;
    }

    public void setProfessiontype(Integer professiontype) {
        this.professiontype = professiontype;
    }

    public Integer getSetuptime() {
        return setuptime;
    }

    public void setSetuptime(Integer setuptime) {
        this.setuptime = setuptime;
    }
}