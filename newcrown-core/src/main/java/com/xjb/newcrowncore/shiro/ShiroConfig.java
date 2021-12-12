package com.xjb.newcrowncore.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@SuppressWarnings("all")
@Configuration
public class ShiroConfig {

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(MyRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //更改ream默认的密码比较方式
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        realm.setCredentialsMatcher(matcher);
        //使用自己的realm
        manager.setRealm(realm);
        return manager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //添加安全管理器
        factoryBean.setSecurityManager(securityManager);
        //自定义url规则
        Map<String, String> filterRuleMap = new HashMap<>();
        //访问登录和注册不需要认证授权
        filterRuleMap.put("/system/user/login", "anon");
        filterRuleMap.put("/system/user/test01", "anon");
        //开放API文档接口
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/webjars/**","anon");
        filterRuleMap.put("/swagger-resources/**","anon");
        filterRuleMap.put("/v2/**","anon");
        filterRuleMap.put("/static/**","anon");
        //sql监控
        filterRuleMap.put("/druid/**","anon");
        //所有请求经过我自己的过滤器
        /*Map<String, Filter> filterMap = new HashMap<>(1);
        filterMap.put("jwt",jwtFilter);
        factoryBean.setFilters(filterMap);*/
        filterRuleMap.put("/**", "authc");
        //设置过滤器链
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        //强制使用cglib，防止重复代理和可能引起代理出错的问题
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
