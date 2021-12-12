package com.xjb.newcrownweb.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 辛集斌
 * @create 2021/12/12
 */
@Configuration
@MapperScan("com.xjb.newcrowncore.mapper")
public class MybatisPlusConfig {
    //在这个类中添加mybatis-plus的各种插件

}
