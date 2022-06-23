package com.lin.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.sun.deploy.perf.PerfRollup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement    // 自动管理事务
@Configuration
@MapperScan("com.lin.mapper")
public class MybatisPlusConfig {
    // 注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    // 逻辑删除组件
    @Bean
    public ISqlInjector injector(){
        return new LogicSqlInjector();
    }

    // SQL执行效率插件
    @Bean
    @Profile({"test","dev"})    // 只在test和dev两套环境中生效
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(200);    // 设置SQL执行的最大时间，如果超过，则不执行，默认单位为ms
        performanceInterceptor.setFormat(true); // 是否开启格式化支持，使查询结果更清楚
        return performanceInterceptor;
    }
}
