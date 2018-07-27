package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitResume;
import com.wmq.sys.vo.PersonalUserInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecruitResumeMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(RecruitResume record);

    int insertSelective(RecruitResume record);

    RecruitResume selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(RecruitResume record);

    int updateByPrimaryKey(RecruitResume record);

    PersonalUserInfoVo getPersonalUserInfo(Integer userId);

    int getResumeNum();

    int getMonthAddResumeNum();

    int getMontEntryNum();

    int getDayEntryNum();

    int getDayAddResumeNum();
}