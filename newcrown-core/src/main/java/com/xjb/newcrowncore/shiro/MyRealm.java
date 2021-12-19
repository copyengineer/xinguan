package com.xjb.newcrowncore.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjb.newcrowncommon.Enum.ExceptionEnum;
import com.xjb.newcrowncommon.Exception.MyCustomerException;
import com.xjb.newcrowncommon.dto.Principal;
import com.xjb.newcrowncommon.model.*;
import com.xjb.newcrowncore.mapper.TbMenuMapper;
import com.xjb.newcrowncore.mapper.TbRoleMapper;
import com.xjb.newcrowncore.mapper.TbRoleMenuMapper;
import com.xjb.newcrowncore.mapper.TbUserRoleMapper;
import com.xjb.newcrowncore.service.TbUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    private static final String SALT = "helloShiro";

    @Resource
    private TbUserService userService;
    @Resource
    private TbUserRoleMapper userRoleMapper;
    @Resource
    private TbRoleMenuMapper roleMenuMapper;
    @Resource
    private TbMenuMapper menuMapper;
    @Resource
    private TbRoleMapper roleMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 授权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //1、授予用户角色
        List<TbRole> roles = principal.getRoles();
        authorizationInfo.setRoles(roles.stream().map(TbRole::getRoleName).collect(Collectors.toSet()));
        //2、给用户授权
        List<String> permits = principal.getPermits();
        authorizationInfo.setStringPermissions(new HashSet<>(permits));
        return authorizationInfo;
    }

    /**
     * 认证方法
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("[MyRealm.doGetAuthenticationInfo]" + "开始执行认证方法" + System.currentTimeMillis());
        String userName = (String) token.getPrincipal();
        //通过用户名查找用户
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userName);
        TbUser user = userService.getOne(wrapper);
        //如果用户不存在
        if(user == null){
            throw new MyCustomerException(ExceptionEnum.user_not_exist);
        }
        return new SimpleAuthenticationInfo(userName,user.getPassword(), ByteSource.Util.bytes(SALT),getName());
    }

    /**
     * 构建用户身份
     * @param user 用户对象
     * @return 用户身份
     */
    private Principal buildUserPrincipal(TbUser user) {
        //1、根据用户的id查找用户的角色
        QueryWrapper<TbUserRole> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_id",user.getId());
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
            menuIds.forEach(id -> {
                menus.add(menuMapper.selectById(id));
            });
        }

        //4、根据角色id集合查找用户的所有角色
        List<TbRole> roles = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleIds)){
            roleIds.forEach(id -> {
                roles.add(roleMapper.selectById(id));
            });
        }

        //5、构建用户身份对象
        Principal principal = new Principal();
        principal.setUser(user);
        principal.setMenus(menus);
        principal.setRoles(roles);
        setPerAndUrl(principal);
        return principal;
    }

    private void setPerAndUrl(Principal principal) {
        List<TbMenu> menus = principal.getMenus();
        List<String> permits = new ArrayList<>();
        menus.forEach(menu -> {
            if("1".equals(menu.getType())){
                permits.add(menu.getPerms());
            }
        });
        principal.setPermits(permits);
    }
}
