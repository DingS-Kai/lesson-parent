package com.fosu.lesson.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.pojo.TScheduletask;
import com.fosu.lesson.service.ScheduleTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
@Api(tags = "基本信息管理-排课任务")
public class ScheduleTaskController {

    @Reference
    private ScheduleTaskService scheduleTaskService;


    @ApiOperation(value = "查找所有年级排课信息")
    @GetMapping("/findAllTask")
    public List<TScheduletask> findAll(){
        return scheduleTaskService.findAll();
    }
}
