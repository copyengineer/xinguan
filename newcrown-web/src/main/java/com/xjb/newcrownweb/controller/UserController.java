package com.xjb.newcrownweb.controller;

import com.xjb.newcrowncommon.Response.Response;
import com.xjb.newcrowncommon.bean.dto.UserLoginDto;
import com.xjb.newcrowncore.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@RestController
@Slf4j
@RequestMapping("/system/user")
@Api(value = "用户相关的API")
public class UserController {

    @Autowired
    TbUserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Response<String> login(@RequestBody UserLoginDto userLoginDto){
        log.info("用户登录");
        return userService.login(userLoginDto);
    }

    @PostMapping("/userInfo")
    @ApiOperation("获取用户信息")
    public Response<String> userInfo(){
        log.info("获取用户信息");
        return Response.success(0,"cg",null);
    }

    @GetMapping("/test01")
    @RequiresRoles("测试角色")
    public Response<String> test01(){
        return Response.success(0,"登录成功",null);
    }

}
