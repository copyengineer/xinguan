package com.xjb.newcrowncore;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
public class Generator {

    public static void main(String[] args) {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("xjb");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
        gc.setIdType(IdType.ID_WORKER); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://rm-bp14okffvij6d4568uo.mysql.rds.aliyuncs.com:3306/xinguan?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("526Ht138");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("newcrowncore"); //模块名
        pc.setParent("com.xjb.");//生成的代码将放在com.xjb.mp下
        pc.setController("controller");//设置各层的名字
        pc.setEntity("bean");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("biz_consumer");//--------*****需要为那些表生成代码
        strategy.setInclude("biz_health");
        strategy.setInclude("biz_in_stock");
        strategy.setInclude("biz_in_stock_info");
        strategy.setInclude("biz_out_stock");
        strategy.setInclude("biz_out_stock_info");
        strategy.setInclude("biz_product");
        strategy.setInclude("biz_product_category");
        strategy.setInclude("biz_product_stock");
        strategy.setInclude("biz_supplier");
        strategy.setInclude("tb_department");
        strategy.setInclude("tb_image");
        strategy.setInclude("tb_log");
        strategy.setInclude("tb_login_log");
        strategy.setInclude("tb_menu");
        strategy.setInclude("tb_role");
        strategy.setInclude("tb_role_menu");
        strategy.setInclude("tb_user");
        strategy.setInclude("tb_user_role");
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
