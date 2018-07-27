package com.wmq.sys.entity;

public class RecruitJobExtend {
    private Integer jobid;

    private Integer userid;

    private Integer companyid;

    private String title;

    private Integer classid;

    private String jobrequire;

    private String jobinfo;

    private String jobhot;

    private Integer replayrate;

    public RecruitJobExtend(Integer jobid, Integer userid, Integer companyid, String title, Integer classid, String jobrequire, String jobinfo, String jobhot, Integer replayrate) {
        this.jobid = jobid;
        this.userid = userid;
        this.companyid = companyid;
        this.title = title;
        this.classid = classid;
        this.jobrequire = jobrequire;
        this.jobinfo = jobinfo;
        this.jobhot = jobhot;
        this.replayrate = replayrate;
    }

    public RecruitJobExtend() {
        super();
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getJobrequire() {
        return jobrequire;
    }

    public void setJobrequire(String jobrequire) {
        this.jobrequire = jobrequire == null ? null : jobrequire.trim();
    }

    public String getJobinfo() {
        return jobinfo;
    }

    public void setJobinfo(String jobinfo) {
        this.jobinfo = jobinfo == null ? null : jobinfo.trim();
    }

    public String getJobhot() {
        return jobhot;
    }

    public void setJobhot(String jobhot) {
        this.jobhot = jobhot == null ? null : jobhot.trim();
    }

    public Integer getReplayrate() {
        return replayrate;
    }

    public void setReplayrate(Integer replayrate) {
        this.replayrate = replayrate;
    }
}