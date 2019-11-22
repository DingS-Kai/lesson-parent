package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.UserManagerMapper;
import com.fosu.lesson.pojo.User;
import com.fosu.lesson.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
//用的dubbo的注解，表明这是一个分布式服务
@Service(interfaceClass = UserManagerService.class)
@Component
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired(required = false)
    @Qualifier("userManagerMapper")
    private UserManagerMapper userManagerMapper;

    @Override
    public String sayHello(String username) {
        return "ldsjfllsd哈哈哈";
    }

    @Override
    public List<User> qryUser() {
        System.out.println("111");
        return userManagerMapper.qryUser();
    }

}
