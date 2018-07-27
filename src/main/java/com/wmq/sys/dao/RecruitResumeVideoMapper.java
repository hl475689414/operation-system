package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitResumeVideo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecruitResumeVideoMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(RecruitResumeVideo record);

    int insertSelective(RecruitResumeVideo record);

    RecruitResumeVideo selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(RecruitResumeVideo record);

    int updateByPrimaryKey(RecruitResumeVideo record);
}