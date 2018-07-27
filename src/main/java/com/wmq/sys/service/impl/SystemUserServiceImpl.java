package com.wmq.sys.service.impl;

import com.wmq.sys.dao.SystemUserMapper;
import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.service.SystemUserService;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.utils.MD5Util;
import com.wmq.sys.vo.SysUserListVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 李怀鹏 on 2018/7/9.
 */
@Service("sysUserService")
public class SystemUserServiceImpl extends BaseService implements SystemUserService {
    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 验证旧密码是否正确
     * @param sysUserId
     * @param password
     * @return
     */
    @Override
    public JsonResult verificationPassword(int sysUserId, String password) {
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(sysUserId);
        if(!MD5Util.MD5(password).equals(systemUser.getPassWord())) {
            return new JsonResult(1, "密码有误，请重新输入");
        }
        return new JsonResult(0, "验证成功");
    }

    /**
     * 修改密码
     * @param sysUserId
     * @param password
     * @param token
     * @return
     */
    @Override
    public JsonResult modifyPassWord(int sysUserId, String password, String token) {
        //对比新旧密码是否相同
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(sysUserId);
        if(MD5Util.MD5(password).equals(systemUser.getPassWord())) {
            return new JsonResult(1, "新密码不得与旧密码相同");
        }
        //修改密码
        systemUser.setId(sysUserId);
        systemUser.setPassWord(MD5Util.MD5(password));
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
        //踢下线
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new JsonResult(0, "修改成功");
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @Override
    public JsonResult signOutLogin(String token) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new JsonResult(0, "退出成功");
    }

    /**
     * 获取平台账号列表
     * @param key
     * @return
     */
    @Override
    public JsonResult getSysUserListPage(String key) {
        List<SysUserListVo> list = systemUserMapper.getSysUserListPage(key);
        return new JsonResult(0, "获取成功", getTotalCount(), list);
    }

    /**
     * 新增账号
     * @param sysUser
     * @return
     */
    @Override
    public JsonResult addSysUser(SystemUser sysUser) {
        systemUserMapper.insertSelective(sysUser);
        return new JsonResult(0, "新增成功");
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @Override
    public JsonResult getSysUserInfo(int id) {
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(id);
        return new JsonResult(0, "获取成功", 0, systemUser);
    }

    /**
     * 编辑用户
     * @param systemUser
     * @return
     */
    @Override
    public JsonResult editSysUser(SystemUser systemUser) {
        SystemUser user = systemUserMapper.selectByPrimaryKey(systemUser.getId());
        boolean bool = true;
        if(systemUser.getPassWord().equals(user.getPassWord())) {
            bool = false;
        }
        if(MD5Util.MD5(systemUser.getPassWord()).equals(user.getPassWord())) {
            bool = false;
        }
        if(bool) {
            systemUser.setPassWord(MD5Util.MD5(systemUser.getPassWord()));
        }
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
        return new JsonResult(0, "编辑成功");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteSysUser(int id) {
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(id);
        if(empty(systemUser)) {
            return new JsonResult(1, "用户不存在");
        }
        if(systemUser.getId() == 1) {
            return new JsonResult(1, "最高管理员不能删除");
        }
        systemUser.setDelFlag(1);
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
        return new JsonResult(0, "删除成功");
    }

    /**
     * 禁用
     * @param id
     * @return
     */
    @Override
    public JsonResult disableSysUser(int id) {
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(id);
        systemUser.setStatus(1);
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
        return new JsonResult(0, "禁用成功");
    }

    /**
     * 启用用户
     * @param id
     * @return
     */
    @Override
    public JsonResult enableSysUser(int id) {
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(id);
        systemUser.setStatus(0);
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
        return new JsonResult(0, "启用成功");
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @Override
    public JsonResult resetPassword(int id) {
        SystemUser systemUser = systemUserMapper.selectByPrimaryKey(id);
        systemUser.setPassWord(MD5Util.MD5("123456"));
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
        return new JsonResult(0, "重置密码成功");
    }
}
