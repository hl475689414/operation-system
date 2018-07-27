package com.wmq.sys.dao;

import com.wmq.sys.entity.CommonCompany;
import com.wmq.sys.vo.CompanyUserInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonCompany record);

    int insertSelective(CommonCompany record);

    CommonCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonCompany record);

    int updateByPrimaryKey(CommonCompany record);

    CompanyUserInfoVo getCompanyUserInfoVo(Integer userId);
}