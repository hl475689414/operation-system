package com.wmq.sys.service.impl;

import com.wmq.sys.dao.*;
import com.wmq.sys.entity.CommonUser;
import com.wmq.sys.entity.RecruitCompanyVideo;
import com.wmq.sys.entity.RecruitResumeVideo;
import com.wmq.sys.service.CommonUserService;
import com.wmq.sys.utils.DateUtil;
import com.wmq.sys.utils.DateUtils;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 李怀鹏 on 2018/5/14.
 */
@Service("commonUserService")
public class CommonUserServiceImpl extends BaseService implements CommonUserService {
    @Autowired
    private CommonUserMapper commonUserMapper;
    @Autowired
    private RecruitResumeMapper recruitResumeMapper;
    @Autowired
    private RecruitResumeVideoMapper recruitResumeVideoMapper;
    @Autowired
    private RecruitJobFilterMapper recruitJobFilterMapper;
    @Autowired
    private RecruitResumeWorkMapper recruitResumeWorkMapper;
    @Autowired
    private RecruitResumeProjectMapper recruitResumeProjectMapper;
    @Autowired
    private RecruitResumeEducationMapper recruitResumeEducationMapper;
    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private CommonCompanyMapper commonCompanyMapper;
    @Autowired
    private RecruitCompanyVideoMapper recruitCompanyVideoMapper;
    @Autowired
    private RecruitJobBaseMapper recruitJobBaseMapper;

    /**
     * 获取用户列表
     * @param keys
     * @param sex
     * @param state
     * @param isCompany
     * @return
     */
    @Override
    public JsonResult getCommonUserList(String keys, int sex, int state, int isCompany) {
        List<CommonUserListVo> list = commonUserMapper.getUserListPage(keys, sex, state, isCompany);
        for(CommonUserListVo userVo : list) {
            userVo.setLoginTime(DateUtils.formatDate(DateUtil.toDefaultDateTime(userVo.getLoginTime()), "yyyy-MM-dd HH:mm"));
            userVo.setRegisterTime(DateUtils.formatDate(DateUtil.toDefaultDateTime(userVo.getRegisterTime()), "yyyy-MM-dd HH:mm"));
            if(empty(userVo.getCompanyName())) {
                userVo.setCompanyName("无");
            }
        }
        return new JsonResult(0, "获取成功", getTotalCount(), list);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @Override
    public JsonResult getUserInfo(int userId) {
        UserInfoVo userInfoVo = commonUserMapper.getUserInfo(userId);
        userInfoVo.setLoginTime(DateUtils.formatDate(DateUtil.toDefaultDateTime(userInfoVo.getLoginTime()), "yyyy-MM-dd HH:mm"));
        userInfoVo.setRegisterTime(DateUtils.formatDate(DateUtil.toDefaultDateTime(userInfoVo.getRegisterTime()), "yyyy-MM-dd HH:mm"));
        return new JsonResult(0, "获取成功", 0, userInfoVo);
    }

    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    @Override
    public JsonResult getPersonalUserInfo(int userId) {
        PersonalUserInfoVo personalUserInfoVo = recruitResumeMapper.getPersonalUserInfo(userId);
        if(empty(personalUserInfoVo)) {
            return new JsonResult(1, "暂无个人身份信息");
        }
        RecruitResumeVideo recruitResumeVideo = recruitResumeVideoMapper.selectByPrimaryKey(userId);
        if(notEmpty(recruitResumeVideo)) {
            personalUserInfoVo.setResumeVideoUrl(recruitResumeVideo.getUrl());
        }

        List<RecruitJobFilterListVo> jobFilterListVos = recruitJobFilterMapper.getJobFilterList(userId);
        personalUserInfoVo.setJobFilterListVos(jobFilterListVos);

        List<RecruitResumeWorkListVo> resumeWorkListVos = recruitResumeWorkMapper.getResumeWorkList(userId);
        for(RecruitResumeWorkListVo recruitResumeWorkListVo : resumeWorkListVos) {
            recruitResumeWorkListVo.setBeginTime(DateUtils.formatDate(DateUtil.toDefaultDateTime(recruitResumeWorkListVo.getBeginTime()), "yyyy-MM-dd"));
            recruitResumeWorkListVo.setEndTime(DateUtils.formatDate(DateUtil.toDefaultDateTime(recruitResumeWorkListVo.getEndTime()), "yyyy-MM-dd"));
        }
        personalUserInfoVo.setResumeWorkListVos(resumeWorkListVos);

        List<RecruitResumeProjectListVo> resumeProjectListVos = recruitResumeProjectMapper.getResumeProjectList(userId);
        personalUserInfoVo.setResumeProjectListVos(resumeProjectListVos);

        List<RecruitResumeEducationListVo> resumeEducationListVos = recruitResumeEducationMapper.getResumeEducationList(userId);
        for(RecruitResumeEducationListVo recruitResumeEducationListVo : resumeEducationListVos) {
            recruitResumeEducationListVo.setEducationName(configMapper.selectByCodeTypes(recruitResumeEducationListVo.getEducation()+"", 1).getValue());
            recruitResumeEducationListVo.setBeginTime(DateUtils.formatDate(DateUtil.toDefaultDateTime(recruitResumeEducationListVo.getBeginTime()), "yyyy-MM-dd"));
            recruitResumeEducationListVo.setEndTime(DateUtils.formatDate(DateUtil.toDefaultDateTime(recruitResumeEducationListVo.getEndTime()), "yyyy-MM-dd"));
        }
        personalUserInfoVo.setResumeEducationListVos(resumeEducationListVos);
        return new JsonResult(0, "获取成功", 0, personalUserInfoVo);
    }

    /**
     * 获取企业信息
     * @param userId
     * @return
     */
    @Override
    public JsonResult getCompanyUserInfo(int userId) {
        CommonUser commonUser = commonUserMapper.selectByPrimaryKey(userId);
        if(empty(commonUser)) {
            return new JsonResult(1, "获取失败");
        }
        if(commonUser.getCompanyid() == 0) {
            return new JsonResult(1, "暂无企业身份信息");
        }
        CompanyUserInfoVo companyUserInfoVo = commonCompanyMapper.getCompanyUserInfoVo(commonUser.getId());
        if(notEmpty(companyUserInfoVo)) {
            companyUserInfoVo.setUserId(userId);
            RecruitCompanyVideo recruitCompanyVideo = recruitCompanyVideoMapper.selectByPrimaryKey(commonUser.getCompanyid());
            if(notEmpty(recruitCompanyVideo)) {
                companyUserInfoVo.setJobVideoUrl(recruitCompanyVideo.getUrl());
            }

            List<RecruitJobBaseListVo> recruitJobBaseListVos = recruitJobBaseMapper.getJobBaseList(commonUser.getCompanyid());
            for(RecruitJobBaseListVo recruitJobBaseListVo : recruitJobBaseListVos) {
                recruitJobBaseListVo.setWorkYearTitle(configMapper.selectByCodeTypes(recruitJobBaseListVo.getWorkYear()+"", 4).getValue());
                recruitJobBaseListVo.setEducationTitle(configMapper.selectByCodeTypes(recruitJobBaseListVo.getEducation()+"", 1).getValue());
            }
            companyUserInfoVo.setRecruitJobBaseListVos(recruitJobBaseListVos);
            return new JsonResult(0, "获取成功", 0, companyUserInfoVo);
        }else {
            return new JsonResult(1, "暂无企业身份信息");
        }
    }
}