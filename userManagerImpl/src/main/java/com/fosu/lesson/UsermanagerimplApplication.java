package com.fosu.lesson;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableDubbo：开启注解Dubbo功能 ，其中可以加入scanBasePackages属性配置包扫描的路径，用于扫描并注册bean
@EnableDubbo
@SpringBootApplication
//指定mybatis的mapper接口存放路径，用于扫描mapper接口并注册
@MapperScan("com.fosu.lesson.dao")
// 启注解事务管理
@EnableTransactionManagement
public class UsermanagerimplApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermanagerimplApplication.class, args);
    }

}
