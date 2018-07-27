package com.wmq.sys.dao;

import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.vo.SysUserListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    SystemUser selectByPrimaryAccount(String accout);

    List<SystemUser> getDeptUserList(int id);

    int updateByListDeptId(List<Integer> list);

    List<String> getRoleUserList(Integer roleId);

    List<SysUserListVo> getSysUserListPage(@Param(value = "key") String key);

    int updateByRoleId(Integer roleId);
}