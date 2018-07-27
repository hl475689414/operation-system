package com.wmq.sys.dao;

import com.wmq.sys.entity.SystemDept;
import com.wmq.sys.vo.SysDeptVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemDept record);

    int insertSelective(SystemDept record);

    SystemDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemDept record);

    int updateByPrimaryKey(SystemDept record);

    List<SysDeptVo> getDeptListPage();

    List<SystemDept> getAllDeptList();

    int deleteByListId(List<Integer> list);
}