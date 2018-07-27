package com.wmq.sys.entity;

public class RecruitJobFilter {
    private Integer id;

    private Integer userid;

    private Integer workclassid;

    private String businessid;

    private Integer workcity;

    private Integer workpaystart;

    private Integer workpayend;

    private Integer isdelete;

    private Integer education;

    private Integer workyear;

    private Integer isopen;

    private Long createtime;

    private Integer recommendnum;

    public RecruitJobFilter(Integer id, Integer userid, Integer workclassid, String businessid, Integer workcity, Integer workpaystart, Integer workpayend, Integer isdelete, Integer education, Integer workyear, Integer isopen, Long createtime, Integer recommendnum) {
        this.id = id;
        this.userid = userid;
        this.workclassid = workclassid;
        this.businessid = businessid;
        this.workcity = workcity;
        this.workpaystart = workpaystart;
        this.workpayend = workpayend;
        this.isdelete = isdelete;
        this.education = education;
        this.workyear = workyear;
        this.isopen = isopen;
        this.createtime = createtime;
        this.recommendnum = recommendnum;
    }

    public RecruitJobFilter() {
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

    public Integer getWorkclassid() {
        return workclassid;
    }

    public void setWorkclassid(Integer workclassid) {
        this.workclassid = workclassid;
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid == null ? null : businessid.trim();
    }

    public Integer getWorkcity() {
        return workcity;
    }

    public void setWorkcity(Integer workcity) {
        this.workcity = workcity;
    }

    public Integer getWorkpaystart() {
        return workpaystart;
    }

    public void setWorkpaystart(Integer workpaystart) {
        this.workpaystart = workpaystart;
    }

    public Integer getWorkpayend() {
        return workpayend;
    }

    public void setWorkpayend(Integer workpayend) {
        this.workpayend = workpayend;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getWorkyear() {
        return workyear;
    }

    public void setWorkyear(Integer workyear) {
        this.workyear = workyear;
    }

    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Integer getRecommendnum() {
        return recommendnum;
    }

    public void setRecommendnum(Integer recommendnum) {
        this.recommendnum = recommendnum;
    }
}