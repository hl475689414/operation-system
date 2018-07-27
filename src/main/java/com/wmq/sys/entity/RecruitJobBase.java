package com.wmq.sys.entity;

import java.util.Date;

public class RecruitJobBase {
    private Integer id;

    private Integer companyid;

    private Integer classid;

    private Integer userid;

    private Integer workcity;

    private Integer education;

    private Integer staffnum;

    private Integer paystart;

    private Integer payend;

    private Integer workyear;

    private Integer rank;

    private Integer viewnum;

    private Integer postnum;

    private Integer isexpress;

    private Integer isdelete;

    private Integer opentalktimes;

    private Integer goodtalktimes;

    private Long createtime;

    private Long updatetime;

    private Integer displaystate;

    private Date expiretime;

    private Integer businessid;

    private Integer recommendnum;

    public RecruitJobBase(Integer id, Integer companyid, Integer classid, Integer userid, Integer workcity, Integer education, Integer staffnum, Integer paystart, Integer payend, Integer workyear, Integer rank, Integer viewnum, Integer postnum, Integer isexpress, Integer isdelete, Integer opentalktimes, Integer goodtalktimes, Long createtime, Long updatetime, Integer displaystate, Date expiretime, Integer businessid, Integer recommendnum) {
        this.id = id;
        this.companyid = companyid;
        this.classid = classid;
        this.userid = userid;
        this.workcity = workcity;
        this.education = education;
        this.staffnum = staffnum;
        this.paystart = paystart;
        this.payend = payend;
        this.workyear = workyear;
        this.rank = rank;
        this.viewnum = viewnum;
        this.postnum = postnum;
        this.isexpress = isexpress;
        this.isdelete = isdelete;
        this.opentalktimes = opentalktimes;
        this.goodtalktimes = goodtalktimes;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.displaystate = displaystate;
        this.expiretime = expiretime;
        this.businessid = businessid;
        this.recommendnum = recommendnum;
    }

    public RecruitJobBase() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getWorkcity() {
        return workcity;
    }

    public void setWorkcity(Integer workcity) {
        this.workcity = workcity;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getStaffnum() {
        return staffnum;
    }

    public void setStaffnum(Integer staffnum) {
        this.staffnum = staffnum;
    }

    public Integer getPaystart() {
        return paystart;
    }

    public void setPaystart(Integer paystart) {
        this.paystart = paystart;
    }

    public Integer getPayend() {
        return payend;
    }

    public void setPayend(Integer payend) {
        this.payend = payend;
    }

    public Integer getWorkyear() {
        return workyear;
    }

    public void setWorkyear(Integer workyear) {
        this.workyear = workyear;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getViewnum() {
        return viewnum;
    }

    public void setViewnum(Integer viewnum) {
        this.viewnum = viewnum;
    }

    public Integer getPostnum() {
        return postnum;
    }

    public void setPostnum(Integer postnum) {
        this.postnum = postnum;
    }

    public Integer getIsexpress() {
        return isexpress;
    }

    public void setIsexpress(Integer isexpress) {
        this.isexpress = isexpress;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getOpentalktimes() {
        return opentalktimes;
    }

    public void setOpentalktimes(Integer opentalktimes) {
        this.opentalktimes = opentalktimes;
    }

    public Integer getGoodtalktimes() {
        return goodtalktimes;
    }

    public void setGoodtalktimes(Integer goodtalktimes) {
        this.goodtalktimes = goodtalktimes;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getDisplaystate() {
        return displaystate;
    }

    public void setDisplaystate(Integer displaystate) {
        this.displaystate = displaystate;
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getRecommendnum() {
        return recommendnum;
    }

    public void setRecommendnum(Integer recommendnum) {
        this.recommendnum = recommendnum;
    }
}