package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitResumeWork;
import com.wmq.sys.vo.RecruitResumeWorkListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitResumeWorkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecruitResumeWork record);

    int insertSelective(RecruitResumeWork record);

    RecruitResumeWork selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecruitResumeWork record);

    int updateByPrimaryKey(RecruitResumeWork record);

    List<RecruitResumeWorkListVo> getResumeWorkList(Integer userId);
}