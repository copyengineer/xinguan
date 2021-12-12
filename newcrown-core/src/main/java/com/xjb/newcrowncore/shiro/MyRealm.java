package com.xjb.newcrowncore.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjb.newcrowncommon.Enum.ExceptionEnum;
import com.xjb.newcrowncommon.Exception.MyCustomerException;
import com.xjb.newcrowncore.bean.TbUser;
import com.xjb.newcrowncore.service.TbUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    private static final String SALT = "helloShiro";

    @Resource
    private TbUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 授权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证方法
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("[MyRealm.doGetAuthenticationInfo]" + "开始执行认证方法" + System.currentTimeMillis());
        String userName = (String) token.getPrincipal();
        //通过用户名查找用户
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userName);
        TbUser user = userService.getOne(wrapper);
        //如果用户不存在
        if(user == null){
            throw new MyCustomerException(ExceptionEnum.user_not_exist);
        }
        return new SimpleAuthenticationInfo(userName,user.getPassword(), ByteSource.Util.bytes(SALT),getName());
    }

}
