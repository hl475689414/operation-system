package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitJobFilter;
import com.wmq.sys.vo.PersonnelListVo;
import com.wmq.sys.vo.RecruitJobFilterListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecruitJobFilterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecruitJobFilter record);

    int insertSelective(RecruitJobFilter record);

    RecruitJobFilter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecruitJobFilter record);

    int updateByPrimaryKey(RecruitJobFilter record);

    List<RecruitJobFilterListVo> getJobFilterList(Integer userId);

    List<PersonnelListVo> getPersonnelListPage(@Param(value = "key") String key, @Param(value = "classId") Integer classId,
                                               @Param(value = "businessId") Integer businessId, @Param(value = "cityId") Integer cityId,
                                               @Param(value = "workYear") Integer workYear, @Param(value = "pushState") Integer pushState,
                                               @Param(value = "resumeState") Integer resumeState);
}