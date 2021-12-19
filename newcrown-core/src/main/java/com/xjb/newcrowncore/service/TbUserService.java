package com.xjb.newcrowncore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjb.newcrowncommon.Response.Response;
import com.xjb.newcrowncommon.bean.dto.UserLoginDto;
import com.xjb.newcrowncommon.model.TbUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xjb
 * @since 2021-12-11
 */
public interface TbUserService extends IService<TbUser> {

    /**
     * 用户登录
     * @return 登录响应结果
     */
    public Response<String> login(UserLoginDto userLoginDto);
}
