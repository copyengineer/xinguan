package com.xjb.newcrowncore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjb.newcrowncommon.Exception.MyCustomerException;
import com.xjb.newcrowncommon.Response.Response;
import com.xjb.newcrowncommon.Util.JwtUtil;
import com.xjb.newcrowncommon.bean.dto.UserLoginDto;
import com.xjb.newcrowncommon.model.TbUser;
import com.xjb.newcrowncore.mapper.TbUserMapper;
import com.xjb.newcrowncore.service.TbUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private TbUserMapper userMapper;

    @Override
    public Response<String> login(UserLoginDto userLoginDto){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userLoginDto.getUsername(),userLoginDto.getPassword());
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
        }catch (Exception e){
            throw new MyCustomerException("用户名或密码错误",500,e);
        }
        //查找用户
        QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userLoginDto.getUsername());
        TbUser user = userMapper.selectOne(queryWrapper);
        //创建token
        Map<String,String> map = new HashMap<>();
        map.put("userName",userLoginDto.getUsername());
        map.put("id",user.getId() + "");
        String token = JwtUtil.getToken(map);
        return Response.success(0,"登录成功",token);
    }

}
