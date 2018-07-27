package com.wmq.sys.entity;

import java.util.Date;

public class RecruitResumeProject {
    private Integer id;

    private Integer userid;

    private String project;

    private String cotent;

    private String projecturl;

    private String projectresult;

    private String workrole;

    private Date begintime;

    private Date endtime;

    public RecruitResumeProject(Integer id, Integer userid, String project, String cotent, String projecturl, String projectresult, String workrole, Date begintime, Date endtime) {
        this.id = id;
        this.userid = userid;
        this.project = project;
        this.cotent = cotent;
        this.projecturl = projecturl;
        this.projectresult = projectresult;
        this.workrole = workrole;
        this.begintime = begintime;
        this.endtime = endtime;
    }

    public RecruitResumeProject() {
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent == null ? null : cotent.trim();
    }

    public String getProjecturl() {
        return projecturl;
    }

    public void setProjecturl(String projecturl) {
        this.projecturl = projecturl == null ? null : projecturl.trim();
    }

    public String getProjectresult() {
        return projectresult;
    }

    public void setProjectresult(String projectresult) {
        this.projectresult = projectresult == null ? null : projectresult.trim();
    }

    public String getWorkrole() {
        return workrole;
    }

    public void setWorkrole(String workrole) {
        this.workrole = workrole == null ? null : workrole.trim();
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
}