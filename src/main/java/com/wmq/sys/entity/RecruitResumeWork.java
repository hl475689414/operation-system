package com.wmq.sys.entity;

import java.util.Date;

public class RecruitResumeWork {
    private Integer id;

    private Integer workclass;

    private String position;

    private String company;

    private String companybuss;

    private String worktag;

    private Date begintime;

    private Date endtime;

    private String workdep;

    private String content;

    private String workresult;

    private Integer hidemyresume;

    private Integer userid;

    public RecruitResumeWork(Integer id, Integer workclass, String position, String company, String companybuss, String worktag, Date begintime, Date endtime, String workdep, String content, String workresult, Integer hidemyresume, Integer userid) {
        this.id = id;
        this.workclass = workclass;
        this.position = position;
        this.company = company;
        this.companybuss = companybuss;
        this.worktag = worktag;
        this.begintime = begintime;
        this.endtime = endtime;
        this.workdep = workdep;
        this.content = content;
        this.workresult = workresult;
        this.hidemyresume = hidemyresume;
        this.userid = userid;
    }

    public RecruitResumeWork() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkclass() {
        return workclass;
    }

    public void setWorkclass(Integer workclass) {
        this.workclass = workclass;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCompanybuss() {
        return companybuss;
    }

    public void setCompanybuss(String companybuss) {
        this.companybuss = companybuss == null ? null : companybuss.trim();
    }

    public String getWorktag() {
        return worktag;
    }

    public void setWorktag(String worktag) {
        this.worktag = worktag == null ? null : worktag.trim();
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getWorkdep() {
        return workdep;
    }

    public void setWorkdep(String workdep) {
        this.workdep = workdep == null ? null : workdep.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getWorkresult() {
        return workresult;
    }

    public void setWorkresult(String workresult) {
        this.workresult = workresult == null ? null : workresult.trim();
    }

    public Integer getHidemyresume() {
        return hidemyresume;
    }

    public void setHidemyresume(Integer hidemyresume) {
        this.hidemyresume = hidemyresume;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}