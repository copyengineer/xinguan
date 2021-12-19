package com.xjb.newcrownweb.controller;

import com.xjb.newcrowncommon.Response.Response;
import com.xjb.newcrowncommon.vo.MenuTreeVo;
import com.xjb.newcrowncore.service.TbMenuService;
import com.xjb.newcrownweb.interceptors.TokenInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author 辛集斌
 * @create 2021/12/18
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private TbMenuService menuService;

    @GetMapping("getMenusForCurrentUser")
    public Response<Set<MenuTreeVo>> getMenusForCurrentUser(){
        //获取当前登录用户的id
        String id = TokenInterceptor.USER_MAP.get("id");
        //根据用户id查找该用户有权看到的菜单
        Set<MenuTreeVo> menus = menuService.getMenusByUserName(id);
        return Response.success(0,"获取用户菜单成功",menus);
    }
}
