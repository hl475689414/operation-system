package com.wmq.sys.dao;

import com.wmq.sys.entity.SystemRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemRoleMenu record);

    int insertSelective(SystemRoleMenu record);

    SystemRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemRoleMenu record);

    int updateByPrimaryKey(SystemRoleMenu record);

    List<Integer> getRoleChoiceList(Integer roleId);

    int deleteByPrimaryRoleId(Integer roleId);

    int insertRoleMenus(List<SystemRoleMenu> list);

    int deleteByRoleMenuList(List<Integer> list);
}