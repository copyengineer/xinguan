package com.xjb.newcrowncore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjb.newcrowncommon.converter.MenuConverter;
import com.xjb.newcrowncommon.model.TbMenu;
import com.xjb.newcrowncommon.model.TbRoleMenu;
import com.xjb.newcrowncommon.model.TbUserRole;
import com.xjb.newcrowncommon.vo.MenuTreeVo;
import com.xjb.newcrowncore.mapper.TbMenuMapper;
import com.xjb.newcrowncore.mapper.TbRoleMenuMapper;
import com.xjb.newcrowncore.mapper.TbUserRoleMapper;
import com.xjb.newcrowncore.service.TbMenuService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author xjb
 * @since 2021-12-11
 */
@Service
public class TbMenuServiceImpl extends ServiceImpl<TbMenuMapper, TbMenu> implements TbMenuService {

    @Resource
    private TbUserRoleMapper userRoleMapper;
    @Resource
    private TbRoleMenuMapper roleMenuMapper;
    @Resource
    private TbMenuMapper menuMapper;
    @Resource
    private MenuConverter menuConverter;

    /**
     * 根据用户名查找该用户的菜单
     * @param id 用户id
     * @return 菜单列表
     */
    @Override
    public Set<MenuTreeVo> getMenusByUserName(String id){
        //1、根据用户的id查找用户的角色
        QueryWrapper<TbUserRole> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_id",id);
        List<TbUserRole> tbUserRoles = userRoleMapper.selectList(wrapper1);
        List<Long> roleIds = tbUserRoles.stream().map(TbUserRole::getRoleId).collect(Collectors.toList());

        //2、根据用户所持有的角色查找用户的菜单
        //2.1、先找出用户所能访问的菜单的id的集合(因为不同的角色可能有权限访问同一个菜单，所以这里必须使用Set集合来防止重复)
        Set<Long> menuIds = new HashSet<>();
        if(!CollectionUtils.isEmpty(tbUserRoles)){
            roleIds.forEach(roleId -> {
                QueryWrapper<TbRoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
                roleMenuQueryWrapper.eq("role_id",roleId);
                List<TbRoleMenu> tbRoleMenus = roleMenuMapper.selectList(roleMenuQueryWrapper);
                tbRoleMenus.forEach(roleMenus -> {
                    menuIds.add(roleMenus.getMenuId());
                });
            });
        }

        //3、根据菜单id集合查找用户的所有菜单
        List<TbMenu> menus = new ArrayList<>();
        if(!CollectionUtils.isEmpty(menuIds)){
            menuIds.forEach(menuId -> {
                menus.add(menuMapper.selectById(menuId));
            });
        }

        //把用户的菜单转换成一个菜单树视图
        return menuConverter.changeMenusToMenuTree(menus);
    }

}
