package com.wmq.sys.controller;

import com.wmq.sys.entity.ImdbMessage;
import com.wmq.sys.entity.SysResumeRecommendCompany;
import com.wmq.sys.service.PersonnelService;
import com.wmq.sys.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by 李怀鹏 on 2018/6/21.
 */
@RestController
@RequestMapping("/personnel")
public class PersonnelPushController extends BaseController{
    @Autowired
    private PersonnelService personnelService;

    /**
     * 获取人才统计数据
     * @return
     */
    @RequestMapping("/getPersonnelStatisticsData")
    public JsonResult getPersonnelStatisticsData() {
        return personnelService.getPersonnelStatisticsData();
    }

    /**
     * 获取人才数据列表
     * @return
     */
    @RequestMapping("/getPersonnelListPage")
    public JsonResult getPersonnelListPage() {
        String key = getParams().getString("key"); //搜索关键字
        int classId = getParams().getInt("classId"); //职位类型ID 0不限
        int businessId = getParams().getInt("businessId"); //行业ID 0不限
        int cityId = getParams().getInt("cityId"); //城市ID 0不限
        int workYear = getParams().getInt("workYear"); //工作经验 -1不限
        int pushState = getParams().getInt("pushState"); //推送状态 -1不限 0否 1是
        int resumeState = getParams().getInt("resumeState"); //简历状态 -1不限 0求职中 1已入职
        return personnelService.getPersonnelListPage(key, classId, businessId, cityId, workYear, pushState, resumeState);
    }

    /**
     * 获取企业职位数据列表
     * @return
     */
    @RequestMapping("/getCompanyJobListPage")
    public JsonResult getCompanyJobListPage() {
        String key = getParams().getString("key"); //搜索关键字
        int classId = getParams().getInt("classId"); //职位类型ID 0不限
        int cityId = getParams().getInt("cityId"); //城市ID 0不限
        int workYear = getParams().getInt("workYear"); //工作经验 -2显示所有 -1=不限 0=应届毕业生 1=1年一下 2=1-3年 3=3-5年 4=5-10年 5=10年以上
        int vip = getParams().getInt("vip"); //VIP等级 -1不限
        return personnelService.getCompanyJobListPage(key, classId, cityId, workYear, vip);
    }

    /**
     * 推送人才，验证是否可推送并保存消息记录
     * @return
     */
    @RequestMapping("/pushPersonnel")
    public JsonResult pushPersonnel() {
        int recommendUserId = getParams().getInt("recommendUserId"); //推送给此用户
        int jobBaseId = getParams().getInt("jobBaseId"); //招聘职位ID
        int filterId = getParams().getInt("filterId"); //求职意向ID
        int userId = getParams().getInt("userId"); //简历用户ID
        int sysUserId = getParams().getInt("sysUserId"); //后台管理用户ID
        SysResumeRecommendCompany sysResumeRecommendCompany = new SysResumeRecommendCompany();
        sysResumeRecommendCompany.setRecommenduserid(recommendUserId);
        sysResumeRecommendCompany.setJobbaseid(jobBaseId);
        sysResumeRecommendCompany.setFilterid(filterId);
        sysResumeRecommendCompany.setUserid(userId);
        sysResumeRecommendCompany.setSysuserid(sysUserId);
        sysResumeRecommendCompany.setCreatetime(new Date());

        String messageId = getParams().getString("messageId"); //消息Id
        String toUserId = recommendUserId+"_"+1; //聊天对象用户Id_角色
        String myUserId = userId+"_"+0; //用户Id_角色
        long time =new Date().getTime(); //时间戳
        String type = "txt"; //消息类型  txt: 文本消息；img: 图片；loc: 位置；audio: 语音；video:视频
        int target = 0; //0发送，1接收
        String message = getParams().getString("message"); //描述内容
        String bodies = getParams().getString("bodies"); //消息主体内容JSON字符串 注意双引号转码 格式：{"msg":"你好","type":"txt"}
        String ext = getParams().getString("ext"); //扩展消息JSON字符串 注意双引号转码，可为空
        int state = 0; //0未读，1已读
        ImdbMessage imdbMessage = new ImdbMessage();
        imdbMessage.setMessageid(messageId);
        imdbMessage.setTouserid(toUserId);
        imdbMessage.setMyuserid(myUserId);
        imdbMessage.setTimestamp(time);
        imdbMessage.setType(type);
        imdbMessage.setTarget(target);
        imdbMessage.setMessage(message);
        imdbMessage.setBodies(bodies);
        imdbMessage.setExt(ext);
        imdbMessage.setState(state);
        return personnelService.pushPersonnel(sysResumeRecommendCompany, imdbMessage);
    }

    /**
     * 获取推送记录列表
     * @return
     */
    @RequestMapping("/getPushRecordListPage")
    public JsonResult getPushRecordListPage() {
        return personnelService.getPushRecordListPage();
    }

    /**
     * 维护简历状态
     * @return
     */
    @RequestMapping("/updateRecruitResumeState")
    public JsonResult updateRecruitResumeState() {
        int userId = getParams().getInt("userId"); //用户ID
        int entryState = getParams().getInt("entryState"); //求职状态 0求职中 1已入职
        int sysUserId = getParams().getInt("sysUserId"); //操作人ID
        return personnelService.updateRecruitResumeState(userId, entryState, sysUserId);
    }
}
