package com.fosu.lesson.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.User;
import com.fosu.lesson.service.UserManagerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserManagerController {

    @Reference
    private UserManagerService userManagerService;

    @GetMapping("hello/{name}")
    public String sayHello(@PathVariable String name){
        System.out.printf("+++++++++++++++++++++++++++++++++"+name);
        return userManagerService.sayHello(name);
    }

    @GetMapping("hell")
    public List<User> qryUser(){
        return userManagerService.qryUser();
    }



}
