package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.pojo.User;
import com.fosu.lesson.service.UserManagerService;
import com.fosu.lesson.dao.UserManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Service(interfaceClass = UserManagerService.class)
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
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
