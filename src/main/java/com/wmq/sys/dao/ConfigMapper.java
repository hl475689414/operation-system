package com.wmq.sys.dao;

import com.wmq.sys.entity.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    Config selectByCodeTypes(@Param(value = "code") String code, @Param(value = "types") Integer types);

    int addView(@Param(value = "code") String code, @Param(value = "types") Integer types);
}