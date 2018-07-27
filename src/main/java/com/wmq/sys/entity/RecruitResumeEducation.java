package com.wmq.sys.entity;

import java.util.Date;

public class RecruitResumeEducation {
    private Integer id;

    private Integer userid;

    private String college;

    private String major;

    private Integer education;

    private Date begintime;

    private Date endtime;

    private String schoolexp;

    private String classname;

    public RecruitResumeEducation(Integer id, Integer userid, String college, String major, Integer education, Date begintime, Date endtime, String schoolexp, String classname) {
        this.id = id;
        this.userid = userid;
        this.college = college;
        this.major = major;
        this.education = education;
        this.begintime = begintime;
        this.endtime = endtime;
        this.schoolexp = schoolexp;
        this.classname = classname;
    }

    public RecruitResumeEducation() {
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

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
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

    public String getSchoolexp() {
        return schoolexp;
    }

    public void setSchoolexp(String schoolexp) {
        this.schoolexp = schoolexp == null ? null : schoolexp.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }
}