package com.wmq.sys.shiro;

import com.wmq.sys.config.ApplicationContextRegister;
import com.wmq.sys.dao.SystemUserMapper;
import com.wmq.sys.entity.SystemUser;
import com.wmq.sys.service.SystemMenuService;
import com.wmq.sys.utils.MD5Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

/**
 * Created by 李怀鹏 on 2018/6/14.
 */
public class ShiroRealm extends AuthorizingRealm {
    private static Logger logger = LogManager.getLogger(ShiroRealm.class);

    //如果项目中用到了事物，@Autowired注解会使事物失效，可以自己用get方法获取值
//    @Autowired
//    private SysUserMapper sysUserMapper;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * 由于前后端分离，每次请求登录时拿到的JESESSIONID都不一样，所以此处无法使用缓存判断
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = token.getUsername();
        String password = String.valueOf(token.getPassword());
        // 从数据库获取对应用户名密码的用户
        SystemUserMapper systemUserMapper = ApplicationContextRegister.getBean(SystemUserMapper.class); //上下文中获取用户DAO对象
        SystemUser systemUser = systemUserMapper.selectByPrimaryAccount(userName);
        // 账号不存在
        if (systemUser == null) {
            throw new UnknownAccountException("账号不存在");
        }

        // 密码错误
        if (!MD5Util.MD5(password).equals(systemUser.getPassWord())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        // 账号锁定
        if (systemUser.getStatus() == 1) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        //SysUser务必序列化，否则将导致登录成功后，shiro获取用户信息时强转为对象时返回null，导致无法访问接口
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(systemUser, password, getName());
        return authenticationInfo;
    }

    /**
     * 给用户分配角色及权限 -- 授权
     * 此方法为只要方法头有权限注解或页面有权限标签（前后端不完全分离的时候会），每次请求方法都会进入此方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof SystemUser) {
            SystemUser userLogin = (SystemUser) principal;
            if(userLogin.getId() == 1) {
                authorizationInfo.addRole("*");
                authorizationInfo.addStringPermission("*");
            }else {
                //赋予权限表示列表
                SystemMenuService menuService = ApplicationContextRegister.getBean(SystemMenuService.class);
                Set<String> perms = menuService.listPerms(userLogin.getId());
                authorizationInfo.addStringPermissions(perms);
            }
        }
        return authorizationInfo;
    }
}