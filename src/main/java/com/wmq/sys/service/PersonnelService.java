package com.wmq.sys.service;

import com.wmq.sys.entity.ImdbMessage;
import com.wmq.sys.entity.SysResumeRecommendCompany;
import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/6/21.
 */
public interface PersonnelService {
    /**
     * 获取人才统计数据
     * @return
     */
    JsonResult getPersonnelStatisticsData();

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
    JsonResult getPersonnelListPage(String key, int classId, int businessId, int cityId, int workYear, int pushState, int resumeState);

    /**
     * 获取企业职位数据列表
     * @param key
     * @param classId
     * @param cityId
     * @param workYear
     * @param vip
     * @return
     */
    JsonResult getCompanyJobListPage(String key, int classId, int cityId, int workYear, int vip);

    /**
     * 推送人才，验证是否可推送并保存消息记录
     * @param sysResumeRecommendCompany
     * @param imdbMessage
     * @return
     */
    JsonResult pushPersonnel(SysResumeRecommendCompany sysResumeRecommendCompany, ImdbMessage imdbMessage);

    /**
     * 获取推送记录列表
     * @return
     */
    JsonResult getPushRecordListPage();

    /**
     * 维护简历状态
     * @param userId
     * @param entryState
     * @param sysUserId
     * @return
     */
    JsonResult updateRecruitResumeState(int userId, int entryState, int sysUserId);
}
