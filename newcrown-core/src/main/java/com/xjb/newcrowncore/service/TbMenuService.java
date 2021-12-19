package com.xjb.newcrowncore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjb.newcrowncommon.model.TbMenu;
import com.xjb.newcrowncommon.vo.MenuTreeVo;

import java.util.Set;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author xjb
 * @since 2021-12-11
 */
public interface TbMenuService extends IService<TbMenu> {

    /**
     * 根据用户名查找该用户的菜单
     * @param userName 用户名
     * @return 菜单列表
     */
    public Set<MenuTreeVo> getMenusByUserName(String userName);
}
