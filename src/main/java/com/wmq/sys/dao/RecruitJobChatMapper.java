package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitJobChat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecruitJobChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecruitJobChat record);

    int insertSelective(RecruitJobChat record);

    RecruitJobChat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecruitJobChat record);

    int updateByPrimaryKey(RecruitJobChat record);

    int getJobChatCount(RecruitJobChat jobChat);
}