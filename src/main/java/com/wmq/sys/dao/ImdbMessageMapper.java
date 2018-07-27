package com.wmq.sys.dao;

import com.wmq.sys.entity.ImdbMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImdbMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImdbMessage record);

    int insertSelective(ImdbMessage record);

    ImdbMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImdbMessage record);

    int updateByPrimaryKey(ImdbMessage record);

    int selectCountByMessageId(@Param(value="messageid")String messageid, @Param(value="userId")String userId);
}