package com.xjb.newcrowncommon.converter;

import com.xjb.newcrowncommon.model.TbMenu;
import com.xjb.newcrowncommon.vo.MenuTreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单转换类
 * @author 辛集斌
 * @create 2021/12/19
 */
@Component
public class MenuConverter {

    /**
     * 将某个用户的所有菜单转换成一个菜单树
     * @param menus 用户的所有菜单
     * @return 菜单树
     */
    public Set<MenuTreeVo> changeMenusToMenuTree(List<TbMenu> menus) {
        Set<MenuTreeVo> menuTreeVo = new HashSet<>();
        if(!CollectionUtils.isEmpty(menus)){
            for(TbMenu menu : menus){
                if(menu.getParentId() == 0){
                    //此菜单是顶级菜单
                    MenuTreeVo node = new MenuTreeVo();
                    BeanUtils.copyProperties(menu,node);
                    menuTreeVo.add(node);
                }
            }
        }
        changeMenusToMenuTreeImpl(menuTreeVo,menus);
        return menuTreeVo;
    }

    public void changeMenusToMenuTreeImpl(Set<MenuTreeVo> menuTreeVo,List<TbMenu> menus){
        if(!CollectionUtils.isEmpty(menuTreeVo)){
            //给每一个根节点寻找子节点
            for(MenuTreeVo v : menuTreeVo){
                Set<MenuTreeVo> childMenus = new HashSet<>();
                for(TbMenu menu : menus){
                    if(menu.getParentId().equals(v.getId())){
                        MenuTreeVo node = new MenuTreeVo();
                        BeanUtils.copyProperties(menu,node);
                        childMenus.add(node);
                    }
                }
                if(!CollectionUtils.isEmpty(childMenus)){
                    v.setChildMenus(childMenus);
                    //回调
                    changeMenusToMenuTreeImpl(childMenus,menus);
                }else {
                    return;
                }
            }
        }
    }

}
