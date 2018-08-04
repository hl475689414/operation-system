package com.wmq.sys.service.impl;

import com.wmq.sys.dao.CommonBusinessMapper;
import com.wmq.sys.dao.CommonCityMapper;
import com.wmq.sys.dao.RecruitJobClassMapper;
import com.wmq.sys.entity.CommonBusiness;
import com.wmq.sys.entity.CommonCity;
import com.wmq.sys.entity.RecruitJobClass;
import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.redis.RedisCache;
import com.wmq.sys.service.CommonService;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.utils.oss.OSSUtil;
import com.wmq.sys.vo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by 李怀鹏 on 2018/5/14.
 */
@Service("commonService")
public class CommonServiceImpl extends BaseService implements CommonService {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RecruitJobClassMapper recruitJobClassMapper;
    @Autowired
    private CommonBusinessMapper commonBusinessMapper;
    @Autowired
    private CommonCityMapper commonCityMapper;

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    @Override
    public JsonResult login(String account, String password) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(account, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            SystemUser user = (SystemUser) subject.getPrincipal();
            Map map = new HashMap();
            map.put("token",subject.getSession().getId()); //返回sessionId做token
            map.put("sysUserId", user.getId());
            map.put("trueNmae", user.getUserName());
            return new JsonResult(0, "登录成功", 0, map);
        } catch (Exception e) {
            return new JsonResult(1, e.getMessage());
        }
    }

    /**
     * 获取所有职位分类
     * @return
     */
    @Override
    public JsonResult getJobClassList() {
        List<RecruitJobClass> list = recruitJobClassMapper.getAllJobClass();
        List<JobClassListVo> jobList = new ArrayList<>();
        JobClassListVo jobClassListVo = null;
        for(RecruitJobClass recruitJobClass : list) {
            if(recruitJobClass.getId() < 1000) {
                jobClassListVo = new JobClassListVo();
                jobClassListVo.setId(recruitJobClass.getId());
                jobClassListVo.setTitle(recruitJobClass.getTitle());
                jobList.add(jobClassListVo);
            }
        }

        List<JobClassChildrenVo> childrenList = null;
        JobClassChildrenVo jobClassChildrenVo = null;
        int start = 0;
        int end = 0;
        for(JobClassListVo jobClassListVo1 : jobList) {
            childrenList = new ArrayList<>();
            start = jobClassListVo1.getId() * 1000;
            end = jobClassListVo1.getId() * 1000 + 999;
            for(RecruitJobClass recruitJobClass : list) {
                if(recruitJobClass.getId() > start && recruitJobClass.getId() < end) {
                    jobClassChildrenVo = new JobClassChildrenVo();
                    jobClassChildrenVo.setId(recruitJobClass.getId());
                    jobClassChildrenVo.setTitle(recruitJobClass.getTitle());
                    childrenList.add(jobClassChildrenVo);
                }
            }
            jobClassListVo1.setChildrenList(childrenList);
        }
        return new JsonResult(0, "获取成功", jobList.size(), jobList);
    }

    /**
     * 获取所有行业分类
     * @return
     */
    @Override
    public JsonResult getBusinessList() {
        List<CommonBusiness> list = commonBusinessMapper.getAllBusiness();
        List<CommonBusinessListVo> businessList = new ArrayList<>();
        CommonBusinessListVo commonBusinessListVo = null;
        for(CommonBusiness commonBusiness : list) {
            if(commonBusiness.getParentid() == 0) {
                commonBusinessListVo = new CommonBusinessListVo();
                commonBusinessListVo.setId(commonBusiness.getId());
                commonBusinessListVo.setTitle(commonBusiness.getName());
                businessList.add(commonBusinessListVo);
            }
        }

        List<CommonBusinessChildrenVo> childrenVos = null;
        CommonBusinessChildrenVo commonBusinessChildrenVo = null;
        for(CommonBusinessListVo commonBusinessListVo1 : businessList) {
            childrenVos = new ArrayList<>();
            for(CommonBusiness commonBusiness : list) {
                if(commonBusinessListVo1.getId() == commonBusiness.getParentid()) {
                    commonBusinessChildrenVo = new CommonBusinessChildrenVo();
                    commonBusinessChildrenVo.setId(commonBusiness.getId());
                    commonBusinessChildrenVo.setTitle(commonBusiness.getName());
                    childrenVos.add(commonBusinessChildrenVo);
                }
            }
            commonBusinessListVo1.setChildrenList(childrenVos);
        }
        return new JsonResult(0, "获取成功", businessList.size(), businessList);
    }

    /**
     * 获取所有城市分类
     * @return
     */
    @Override
    public JsonResult getCityList() {
        List<CommonCity> list = commonCityMapper.getAllCity();
        List<CommonCityListVo> cityList = new ArrayList<>();
        CommonCityListVo commonCityListVo = null;
        for(CommonCity commonCity : list) {
            if(commonCity.getId() < 1000) {
                commonCityListVo = new CommonCityListVo();
                commonCityListVo.setId(commonCity.getId());
                commonCityListVo.setTitle(commonCity.getName());
                cityList.add(commonCityListVo);
            }
        }

        List<CommonCityChildrenVo> childrenVos = null;
        CommonCityChildrenVo commonCityChildrenVo = null;
        int start = 0;
        int end = 0;
        for(CommonCityListVo commonCityListVo1 : cityList) {
            childrenVos = new ArrayList<>();
            start = commonCityListVo1.getId() * 1000;
            end = commonCityListVo1.getId() * 1000 + 999;
            for(CommonCity commonCity : list) {
                if(commonCity.getId() > start && commonCity.getId() < end) {
                    commonCityChildrenVo = new CommonCityChildrenVo();
                    commonCityChildrenVo.setId(commonCity.getId());
                    commonCityChildrenVo.setTitle(commonCity.getName());
                    childrenVos.add(commonCityChildrenVo);
                }
            }
            commonCityListVo1.setChildrenList(childrenVos);
        }
        return new JsonResult(0, "获取成功", cityList.size(), cityList);
    }

    /**
     * 上传图片
     * @param myFile
     * @return
     */
    @Override
    public JsonResult uploadImg(MultipartFile[] myFile) {
        StringBuffer imgName = new StringBuffer();
        for (MultipartFile myfile : myFile) {
            if (myfile.isEmpty()) {
                return new JsonResult(1, "上传图片不能为空", 0, null);
            } else {
                String newFileName = UUID.randomUUID()
                        + myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf("."));
                try {
                    OSSUtil.uploadFile(newFileName, myfile);
                    imgName.append("https://lskxx.oss-cn-shenzhen.aliyuncs.com/" + newFileName + ",");
                } catch (Exception e) {
                    logger.info(e.getMessage());
                    return new JsonResult(1, e.getMessage(), 0, null);
                }
            }
        }
        return new JsonResult(0, "成功", myFile.length, imgName.toString().substring(0, imgName.toString().length() - 1));
    }
}
