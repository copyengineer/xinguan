package com.xjb.newcrownweb.controller;

import com.xjb.newcrowncommon.Response.Response;
import com.xjb.newcrowncommon.bean.dto.UserLoginDto;
import com.xjb.newcrowncore.service.TbUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@RestController
@RequestMapping("/system/user")
@Api(value = "用户相关的API")
public class UserController {

    @Autowired
    TbUserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Response<String> login(@RequestBody UserLoginDto userLoginDto){
        System.out.println("用户登录");
        return userService.login(userLoginDto);
    }

    @GetMapping("/test01")
    public Response<String> test01(){
        return Response.success(0,"登录成功",null);
    }

   /* @GetMapping("/test02")
    public Response<String> test02(){
        SecurityUtils.getSubject().logout();
        return Response.success(0,"登录成功",null);
    }*/
}
