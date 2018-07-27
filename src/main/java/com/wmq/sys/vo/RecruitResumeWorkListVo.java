package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/5/15.
 */
public class RecruitResumeWorkListVo {
    private int id; //简历ID
    private int classId; //职位类型ID
    private String className; //职位类型名称
    private String position; //职位名称
    private String company; //公司名称
    private String beginTime; //开始时间
    private String endTime; //结束时间
    private String workDep; //所属部门

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWorkDep() {
        return workDep;
    }

    public void setWorkDep(String workDep) {
        this.workDep = workDep;
    }
}
