package com.xjb.newcrowncore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjb.newcrowncommon.Exception.MyCustomerException;
import com.xjb.newcrowncommon.Response.Response;
import com.xjb.newcrowncommon.bean.dto.UserLoginDto;
import com.xjb.newcrowncore.bean.TbUser;
import com.xjb.newcrowncore.mapper.TbUserMapper;
import com.xjb.newcrowncore.service.TbUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xjb
 * @since 2021-12-11
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    @Override
    public Response<String> login(UserLoginDto userLoginDto){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginDto.getUsername(),userLoginDto.getPassword());
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
        }catch (Exception e){
            throw new MyCustomerException();
        }
        return Response.success(0,"登录成功",null);
    }

}
