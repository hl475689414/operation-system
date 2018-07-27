package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitResumeProject;
import com.wmq.sys.vo.RecruitResumeProjectListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitResumeProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecruitResumeProject record);

    int insertSelective(RecruitResumeProject record);

    RecruitResumeProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecruitResumeProject record);

    int updateByPrimaryKey(RecruitResumeProject record);

    List<RecruitResumeProjectListVo> getResumeProjectList(Integer userId);
}