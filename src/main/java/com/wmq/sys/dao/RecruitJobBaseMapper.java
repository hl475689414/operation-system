package com.wmq.sys.dao;

import com.wmq.sys.entity.RecruitJobBase;
import com.wmq.sys.vo.CompanyJobListVo;
import com.wmq.sys.vo.RecruitJobBaseListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecruitJobBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecruitJobBase record);

    int insertSelective(RecruitJobBase record);

    RecruitJobBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecruitJobBase record);

    int updateByPrimaryKey(RecruitJobBase record);

    List<RecruitJobBaseListVo> getJobBaseList(Integer companyId);

    List<CompanyJobListVo> getCompanyJobListPage(@Param(value = "key") String key, @Param(value = "classId") Integer classId,
                                                 @Param(value = "cityId") Integer cityId, @Param(value = "workYear") Integer workYear,
                                                 @Param(value = "vip") Integer vip);
}