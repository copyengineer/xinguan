package com.xjb.newcrowncommon.dto;

import com.xjb.newcrowncommon.model.TbMenu;
import com.xjb.newcrowncommon.model.TbRole;
import com.xjb.newcrowncommon.model.TbUser;
import lombok.*;

import java.util.List;

/**
 * 用户身份实体类
 * @author 辛集斌
 * @create 2021/12/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Principal {

    /**
     * 用户
     */
    private TbUser user;

    /**
     * 用户的菜单
     */
    private List<TbMenu> menus;

    /**
     * 用户的角色
     */
    private List<TbRole> roles;

    /**
     *
     */
    private List<String> urls;

    /**
     * 用户权限
     */
    private List<String> permits;

}
