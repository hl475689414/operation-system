package com.wmq.sys.dao;

import com.wmq.sys.entity.SysResumeRecommendCompany;
import com.wmq.sys.vo.PersonnelPushRecordListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysResumeRecommendCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysResumeRecommendCompany record);

    int insertSelective(SysResumeRecommendCompany record);

    SysResumeRecommendCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResumeRecommendCompany record);

    int updateByPrimaryKey(SysResumeRecommendCompany record);

    int getMonthRecommendNum();

    int getPushRecord(SysResumeRecommendCompany record);

    List<PersonnelPushRecordListVo> getPushRecordListPage();
}