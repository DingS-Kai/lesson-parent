/*package com.fosu.lesson;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.fosu.lesson.service.UserManagerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PostConstruct;


@EnableDubbo
//@SpringBootApplication
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class UsermanagerwebApplication {

    @Reference
    private UserManagerService userManagerService;

    @PostConstruct
    public void init() {
        System.err.println("UserManagerService:" + userManagerService);
    }

    public static void main(String[] args) {
        SpringApplication.run(UsermanagerwebApplication.class, args);
    }

}*/
package com.fosu.lesson;

import com.alibaba.dubbo.config.annotation.Reference;
        import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
        import com.fosu.lesson.service.UserManagerService;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
        import springfox.documentation.swagger2.annotations.EnableSwagger2;

        import javax.annotation.PostConstruct;

@EnableSwagger2
@EnableDubbo
@SpringBootApplication
public class UsermanagerwebApplication {

    @Reference
    private UserManagerService userManagerService;

    public static void main(String[] args) {
        SpringApplication.run(UsermanagerwebApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.err.println("UserManagerService:" + userManagerService);
    }

}
