package com.fosu.lesson.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.dao.TScheduleMapper;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.pojo.TScheduleExample;
import com.fosu.lesson.pojo.User;
import com.fosu.lesson.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TScheduleMapper tScheduleMapper;

    @GetMapping("hello/{name}")
    public String sayHello(@PathVariable String name){
        System.out.printf("+++++++++++++++++++++++++++++++++"+name);
        return userManagerService.sayHello(name);
    }

    @GetMapping("hell")
    public List<User> qryUser(){
        return userManagerService.qryUser();
    }

    //@GetMapping("test")
    public List<TSchedule> testSchdule(){

        TScheduleExample tScheduleExample = new TScheduleExample();
        tScheduleExample.createCriteria().andClassIdEqualTo("101");
        List<TSchedule> list= tScheduleMapper.selectByExample(tScheduleExample);
        System.out.println("===================================================");
        for (TSchedule t:list){
            System.out.println(t.toString());
        }
        System.out.println("===================================================");
        return list;
    }



}
