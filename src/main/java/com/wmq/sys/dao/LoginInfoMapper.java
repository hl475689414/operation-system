package com.wmq.sys.dao;

import com.wmq.sys.entity.LoginInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginInfo record);

    int insertSelective(LoginInfo record);

    LoginInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginInfo record);

    int updateByPrimaryKey(LoginInfo record);

    int getTodayStartTotal();
}