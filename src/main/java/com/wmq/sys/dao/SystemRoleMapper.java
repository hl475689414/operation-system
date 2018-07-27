package com.wmq.sys.dao;

import com.wmq.sys.entity.SystemRole;
import com.wmq.sys.vo.SysRoleListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);

    List<SysRoleListVo> getRoleListPage();

    String getRoleDeptName(Integer id);

    List<SystemRole> getDeptRoleList(Integer deptId);

    int updateByListDeptId(List<Integer> list);
}