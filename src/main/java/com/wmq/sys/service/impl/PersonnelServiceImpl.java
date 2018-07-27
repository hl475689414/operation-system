package com.wmq.sys.service.impl;

import com.wmq.sys.dao.*;
import com.wmq.sys.entity.*;
import com.wmq.sys.service.PersonnelService;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.utils.MD5Util;
import com.wmq.sys.vo.CompanyJobListVo;
import com.wmq.sys.vo.PersonnelListVo;
import com.wmq.sys.vo.PersonnelPushRecordListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李怀鹏 on 2018/6/21.
 */
@Service("personnelService")
public class PersonnelServiceImpl extends BaseService implements PersonnelService {
    @Autowired
    private RecruitResumeMapper recruitResumeMapper;
    @Autowired
    private RecruitJobFilterMapper recruitJobFilterMapper;
    @Autowired
    private SysResumeRecommendCompanyMapper sysResumeRecommendCompanyMapper;
    @Autowired
    private RecruitJobBaseMapper recruitJobBaseMapper;
    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private RecruitJobChatMapper recruitJobChatMapper;
    @Autowired
    private ImdbMessageMapper imdbMessageMapper;

    /**
     * 获取人才统计数据
     * @return
     */
    @Override
    public JsonResult getPersonnelStatisticsData() {
        Map map = new HashMap();
        map.put("resumeNum", recruitResumeMapper.getResumeNum()); //简历数量
        map.put("monthAddResumeNum", recruitResumeMapper.getMonthAddResumeNum()); //本月新增简历数
        map.put("monthRecommendNum", sysResumeRecommendCompanyMapper.getMonthRecommendNum()); //本月推荐次数
        map.put("monthEntryNum", recruitResumeMapper.getMontEntryNum()); //本月入职人数
        map.put("dayEntryNum", recruitResumeMapper.getDayEntryNum()); //今日入职人数
        map.put("dayAddResumeNum", recruitResumeMapper.getDayAddResumeNum()); //今日新增简历数
        return new JsonResult(0, "获取成功", 0, map);
    }

    /**
     * 获取人才数据列表
     * @param key
     * @param classId
     * @param businessId
     * @param cityId
     * @param workYear
     * @param pushState
     * @param resumeState
     * @return
     */
    @Override
    public JsonResult getPersonnelListPage(String key, int classId, int businessId, int cityId, int workYear, int pushState, int resumeState) {
        List<PersonnelListVo> list = recruitJobFilterMapper.getPersonnelListPage(key, classId, businessId, cityId, workYear, pushState, resumeState);
        for(PersonnelListVo personnelListVo : list) {
            if(personnelListVo.getWorkYear().equals("0")) {
                personnelListVo.setWorkYear("应届毕业生");
            }else if(Integer.parseInt(personnelListVo.getWorkYear()) > 0 && Integer.parseInt(personnelListVo.getWorkYear()) < 15){
                personnelListVo.setWorkYear(personnelListVo.getWorkYear()+"年");
            }else {
                personnelListVo.setWorkYear("15年");
            }
            personnelListVo.setPassWord(MD5Util.MD5(personnelListVo.getUserId()+"D734%$0Cx!*#@"));
        }
        return new JsonResult(0, "获取成功", getTotalCount(), list);
    }

    /**
     * 获取企业职位数据列表
     * @param key
     * @param classId
     * @param cityId
     * @param workYear
     * @param vip
     * @return
     */
    @Override
    public JsonResult getCompanyJobListPage(String key, int classId, int cityId, int workYear, int vip) {
        List<CompanyJobListVo> list = recruitJobBaseMapper.getCompanyJobListPage(key, classId, cityId, workYear, vip);
        for(CompanyJobListVo companyJobListVo : list) {
            companyJobListVo.setWorkYear(configMapper.selectByCodeTypes(companyJobListVo.getWorkYear(),4).getValue());
        }
        return new JsonResult(0, "获取成功", getTotalCount(), list);
    }

    /**
     * 推送人才，验证是否可推送并保存消息记录
     * @param sysResumeRecommendCompany
     * @param imdbMessage
     * @return
     */
    @Override
    public JsonResult pushPersonnel(SysResumeRecommendCompany sysResumeRecommendCompany, ImdbMessage imdbMessage) {
        RecruitJobChat jobChat = new RecruitJobChat(sysResumeRecommendCompany.getJobbaseid(), sysResumeRecommendCompany.getRecommenduserid(), sysResumeRecommendCompany.getUserid(), 0);
        //判断是否存在
        if (recruitJobChatMapper.getJobChatCount(jobChat) > 0) {
            return new JsonResult(2, "已经发起过沟通了");
        }else {
            recruitJobChatMapper.insertSelective(jobChat);
        }

        int count = sysResumeRecommendCompanyMapper.getPushRecord(sysResumeRecommendCompany);
        if(count == 0) {
            sysResumeRecommendCompanyMapper.insertSelective(sysResumeRecommendCompany);
        }else {
            return new JsonResult(2, "已推送过");
        }

        //保存聊天记录
        if(imdbMessageMapper.selectCountByMessageId(imdbMessage.getMessageid(),imdbMessage.getMyuserid())==0) {
            imdbMessageMapper.insertSelective(imdbMessage);
        }

        //累加求职意向推送次数
        RecruitJobFilter recruitJobFilter = recruitJobFilterMapper.selectByPrimaryKey(sysResumeRecommendCompany.getFilterid());
        recruitJobFilter.setRecommendnum(recruitJobFilter.getRecommendnum()+1);
        recruitJobFilterMapper.updateByPrimaryKeySelective(recruitJobFilter);
        //累加招聘职位已被推送次数
        RecruitJobBase recruitJobBase = recruitJobBaseMapper.selectByPrimaryKey(sysResumeRecommendCompany.getJobbaseid());
        recruitJobBase.setRecommendnum(recruitJobBase.getRecommendnum()+1);
        recruitJobBaseMapper.updateByPrimaryKeySelective(recruitJobBase);
        return new JsonResult(0, "保存成功");
    }

    /**
     * 获取推送记录列表
     * @return
     */
    @Override
    public JsonResult getPushRecordListPage() {
        List<PersonnelPushRecordListVo> list = sysResumeRecommendCompanyMapper.getPushRecordListPage();
        return new JsonResult(0, "获取成功", getTotalCount(), list);
    }

    /**
     * 维护简历状态
     * @param userId
     * @param entryState
     * @param sysUserId
     * @return
     */
    @Override
    public JsonResult updateRecruitResumeState(int userId, int entryState, int sysUserId) {
        RecruitResume recruitResume = new RecruitResume();
        recruitResume.setUserid(userId);
        recruitResume.setEntrystate(entryState);
        recruitResume.setSysuserid(sysUserId);
        recruitResumeMapper.updateByPrimaryKeySelective(recruitResume);
        return new JsonResult(0, "维护成功");
    }
}
