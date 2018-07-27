package com.wmq.sys.dao;

import com.wmq.sys.entity.CommonBusiness;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonBusiness record);

    int insertSelective(CommonBusiness record);

    CommonBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonBusiness record);

    int updateByPrimaryKey(CommonBusiness record);

    List<CommonBusiness> getAllBusiness();
}