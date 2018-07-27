package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitResumeEducation;
import com.wmq.sys.vo.RecruitResumeEducationListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitResumeEducationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecruitResumeEducation record);

    int insertSelective(RecruitResumeEducation record);

    RecruitResumeEducation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecruitResumeEducation record);

    int updateByPrimaryKey(RecruitResumeEducation record);

    List<RecruitResumeEducationListVo> getResumeEducationList(Integer userId);
}