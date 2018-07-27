package com.wmq.sys.service.impl;

import com.wmq.sys.dao.SystemDeptMapper;
import com.wmq.sys.dao.SystemRoleMapper;
import com.wmq.sys.dao.SystemUserMapper;
import com.wmq.sys.entity.SystemDept;
import com.wmq.sys.service.SystemDeptService;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.vo.SysDeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李怀鹏 on 2018/7/9.
 */
@Service("sysDeptService")
public class SystemDeptServiceImpl extends BaseService implements SystemDeptService {
    @Autowired
    private SystemDeptMapper systemDeptMapper;
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemRoleMapper systemRoleMapper;

    /**
     * 获取部门列表
     * @return
     */
    @Override
    public JsonResult getDeptListPage() {
        List<SysDeptVo> list = systemDeptMapper.getDeptListPage();
        for(SysDeptVo sysDeptVo : list) {
            if(sysDeptVo.getParentId() > 0) {
                for(SysDeptVo deptVo : list) {
                    if(deptVo.getId() == sysDeptVo.getParentId()) {
                        sysDeptVo.setParentName(deptVo.getName());
                    }
                }
            }
        }
        return new JsonResult(0, "获取成功", getTotalCount(), list);
    }

    /**
     * 新增部门
     * @param name
     * @param parentId
     * @param remarks
     * @return
     */
    private static int deptSeries = 0; //部门级数
    @Override
    public JsonResult addDept(String name, int parentId, String remarks) {
        //判断部门是否已经大于三级
        if(parentId > 0) {
            deptSeries = 0;
            //获取所有部门数据
            List<SystemDept> list = systemDeptMapper.getAllDeptList();
            deptSeries = getDeptSeries(list, parentId);
            if(deptSeries > 2) {
                return new JsonResult(1, "子级部门不能超过三级");
            }
        }

        SystemDept systemDept = new SystemDept();
        systemDept.setName(name);
        systemDept.setParentId(parentId);
        systemDept.setRemarks(remarks);
        systemDept.setOrderNum(0);
        systemDept.setDelFlag(0);
        systemDeptMapper.insertSelective(systemDept);
        return new JsonResult(0, "新增成功");
    }

    /**
     * 获取当前新建部门上级级数
     * @param list
     * @param parentId
     * @return
     */
    public int getDeptSeries(List<SystemDept> list, int parentId) {
        for(SystemDept systemDept : list) {
            if(systemDept.getId() == parentId) {
                deptSeries++;
                parentId = systemDept.getParentId();
            }
        }
        if(parentId > 0) {
            getDeptSeries(list, parentId);
        }
        return deptSeries;
    }

    /**
     * 编辑部门
     * @param id
     * @param name
     * @param parentId
     * @param remarks
     * @return
     */
    @Override
    public JsonResult editDept(int id, String name, int parentId, String remarks) {
        //判断部门是否已经大于三级
        if(parentId > 0) {
            deptSeries = 0;
            //获取所有部门数据
            List<SystemDept> list = systemDeptMapper.getAllDeptList();
            deptSeries = getDeptSeries(list, parentId);
            if(deptSeries > 2) {
                return new JsonResult(1, "子级部门不能超过三级");
            }
        }
        SystemDept systemDept = systemDeptMapper.selectByPrimaryKey(id);
        if(empty(systemDept)) {
            return new JsonResult(1, "部门不存在");
        }
        //修改部门信息
        systemDept.setName(name);
        systemDept.setParentId(parentId);
        systemDept.setRemarks(remarks);
        systemDeptMapper.updateByPrimaryKeySelective(systemDept);
        return new JsonResult(0, "编辑");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public JsonResult deleteDept(int id) {
        SystemDept systemDept = systemDeptMapper.selectByPrimaryKey(id);
        if(empty(systemDept)) {
            return new JsonResult(1, "部门不存在");
        }
        //获取所有部门数据
        List<SystemDept> list = systemDeptMapper.getAllDeptList();
        listDeptId = new ArrayList<>();
        listDeptId.add(id);
        //获取所有要删除的部门ID
        listDeptId = currentDeptAllId(id, list);
        //批量修改用户部门信息
        systemUserMapper.updateByListDeptId(listDeptId);
        //批量修改角色关联的部门信息
        systemRoleMapper.updateByListDeptId(listDeptId);
        //批量删除部门
        systemDeptMapper.deleteByListId(listDeptId);
        return new JsonResult(0, "删除成功");
    }

    /**
     * 获取当前部门下所有部门ID
     * @param currentDeptId 当前部门ID
     * @return
     */
    private List<Integer> listDeptId = null; //所有要删除的部门ID
    public List<Integer> currentDeptAllId(int currentDeptId, List<SystemDept> list) {
        List<SystemDept> depts = new ArrayList<>();
        for(SystemDept systemDept : list) {
            if(systemDept.getParentId() == currentDeptId) {
                depts.add(systemDept);
            }
        }
        for (SystemDept dept : depts) {
            listDeptId.add(dept.getId());
            currentDeptAllId(dept.getId(), list);
        }
        return listDeptId;
    }

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public JsonResult getAllDeptList() {
        List<SystemDept> list = systemDeptMapper.getAllDeptList();
        return new JsonResult(0, "获取成功", list.size(), list);
    }
}
