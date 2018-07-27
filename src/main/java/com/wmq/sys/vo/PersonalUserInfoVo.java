package com.wmq.sys.vo;

import java.util.List;

/**
 * Created by 李怀鹏 on 2018/5/15.
 */
public class PersonalUserInfoVo {
    private int startWorkYear; //参加工作时间
    private String resumeVideoUrl; //个人视频秀地址
    private String intro; //我的优势
    private String senceUrl; //H5简历地址
    private List<RecruitJobFilterListVo> jobFilterListVos; //求职意向列表
    private List<RecruitResumeWorkListVo> resumeWorkListVos; //工作经历列表
    private List<RecruitResumeProjectListVo> resumeProjectListVos; //项目经验列表
    private List<RecruitResumeEducationListVo> resumeEducationListVos; //教育经历列表

    public int getStartWorkYear() {
        return startWorkYear;
    }

    public void setStartWorkYear(int startWorkYear) {
        this.startWorkYear = startWorkYear;
    }

    public String getResumeVideoUrl() {
        return resumeVideoUrl;
    }

    public void setResumeVideoUrl(String resumeVideoUrl) {
        this.resumeVideoUrl = resumeVideoUrl;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getSenceUrl() {
        return senceUrl;
    }

    public void setSenceUrl(String senceUrl) {
        this.senceUrl = senceUrl;
    }

    public List<RecruitJobFilterListVo> getJobFilterListVos() {
        return jobFilterListVos;
    }

    public void setJobFilterListVos(List<RecruitJobFilterListVo> jobFilterListVos) {
        this.jobFilterListVos = jobFilterListVos;
    }

    public List<RecruitResumeWorkListVo> getResumeWorkListVos() {
        return resumeWorkListVos;
    }

    public void setResumeWorkListVos(List<RecruitResumeWorkListVo> resumeWorkListVos) {
        this.resumeWorkListVos = resumeWorkListVos;
    }

    public List<RecruitResumeProjectListVo> getResumeProjectListVos() {
        return resumeProjectListVos;
    }

    public void setResumeProjectListVos(List<RecruitResumeProjectListVo> resumeProjectListVos) {
        this.resumeProjectListVos = resumeProjectListVos;
    }

    public List<RecruitResumeEducationListVo> getResumeEducationListVos() {
        return resumeEducationListVos;
    }

    public void setResumeEducationListVos(List<RecruitResumeEducationListVo> resumeEducationListVos) {
        this.resumeEducationListVos = resumeEducationListVos;
    }
}
