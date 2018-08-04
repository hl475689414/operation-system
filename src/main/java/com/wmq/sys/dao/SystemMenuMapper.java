package com.wmq.sys.dao;

import com.wmq.sys.entity.SystemMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemMenu record);

    int insertSelective(SystemMenu record);

    SystemMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemMenu record);

    int updateByPrimaryKey(SystemMenu record);

    List<String> listUserPerms(Integer userId);

    List<SystemMenu> getMenuList();

    List<SystemMenu> getAllMenuList();

    int deleteByListId(List<Integer> list);

    List<SystemMenu> getUserMenuList(Integer id);
}