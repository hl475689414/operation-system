package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitJobExtend;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecruitJobExtendMapper {
    int deleteByPrimaryKey(Integer jobid);

    int insert(RecruitJobExtend record);

    int insertSelective(RecruitJobExtend record);

    RecruitJobExtend selectByPrimaryKey(Integer jobid);

    int updateByPrimaryKeySelective(RecruitJobExtend record);

    int updateByPrimaryKey(RecruitJobExtend record);
}