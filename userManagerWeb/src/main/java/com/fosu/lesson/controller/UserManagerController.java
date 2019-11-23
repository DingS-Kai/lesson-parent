package com.fosu.lesson.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.User;
import com.fosu.lesson.service.UserManagerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

   /* @Reference
    private ScheduleService scheduleService;

    @GetMapping("hhh")
    public List<TSchedule> qryOne(){
        System.out.println("====================");
        List<TSchedule> list = scheduleService.qryOne();
        for (int i = 0; i <list.size() ; i++) {
            list.toString();
        }
        return scheduleService.qryOne();
    }*/

}
