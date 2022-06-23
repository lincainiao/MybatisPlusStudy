package com.lin.pojo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

// 代码生成器
public class AutoUser {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 1.全局配置策略
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath+"/src/main/java");
        globalConfig.setAuthor("LinMouren");    //作者
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true); // 是否覆盖原来的
        globalConfig.setServiceName("%sService");  // 去Service的I前缀
        globalConfig.setIdType(IdType.ID_WORKER);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);

        // 2.配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mbplus?useSSL=true&useUnicode=true&characterEncoding=utf-8");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);

        // 3.配置包
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("module_test");
        packageConfig.setParent("com.lin");
        packageConfig.setEntity("pojo");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        autoGenerator.setPackageInfo(packageConfig);

        // 4. 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("user");  // 设置要映射的表名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);    // _下划线转驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);  //使用lombok生成
        strategyConfig.setLogicDeleteFieldName("deleted");  // 逻辑删除字段
        // 自动填充策略
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(createTime);
        tableFillList.add(updateTime);
        strategyConfig.setTableFillList(tableFillList);
        // 乐观锁
        strategyConfig.setVersionFieldName("version");
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.execute();
    }
}
