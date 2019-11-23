/*
package com.fosu.lesson.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.service.ScheduleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ScheduleController {

    @Reference
    private ScheduleService scheduleService;

    @GetMapping("hhhh")
    public List<TSchedule> qryOne(){
        List<TSchedule> list = scheduleService.qryOne();
        System.out.println("====================");
        for (int i = 0; i <list.size() ; i++) {
            list.toString();
        }
        return scheduleService.qryOne();
    }

}*/
