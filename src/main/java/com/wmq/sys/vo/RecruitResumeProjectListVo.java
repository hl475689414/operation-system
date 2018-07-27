package com.wmq.sys.vo;

/**
 * Created by 李怀鹏 on 2018/5/15.
 */
public class RecruitResumeProjectListVo {
    private int id; //项目ID
    private String project; //项目名称
    private String projectURL; //项目URL
    private String workRoole; //项目角色
    private String cotent; //项目描述
    private String projectResult; //项目业绩

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectURL() {
        return projectURL;
    }

    public void setProjectURL(String projectURL) {
        this.projectURL = projectURL;
    }

    public String getWorkRoole() {
        return workRoole;
    }

    public void setWorkRoole(String workRoole) {
        this.workRoole = workRoole;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public String getProjectResult() {
        return projectResult;
    }

    public void setProjectResult(String projectResult) {
        this.projectResult = projectResult;
    }
}
