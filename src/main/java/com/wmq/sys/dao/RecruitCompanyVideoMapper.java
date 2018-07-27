package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitCompanyVideo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecruitCompanyVideoMapper {
    int deleteByPrimaryKey(Integer companyid);

    int insert(RecruitCompanyVideo record);

    int insertSelective(RecruitCompanyVideo record);

    RecruitCompanyVideo selectByPrimaryKey(Integer companyid);

    int updateByPrimaryKeySelective(RecruitCompanyVideo record);

    int updateByPrimaryKey(RecruitCompanyVideo record);
}