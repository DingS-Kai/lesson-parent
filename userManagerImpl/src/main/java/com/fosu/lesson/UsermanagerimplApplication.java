package com.fosu.lesson;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@MapperScan("com.fosu.lesson.dao")
public class UsermanagerimplApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsermanagerimplApplication.class, args);
    }

}
