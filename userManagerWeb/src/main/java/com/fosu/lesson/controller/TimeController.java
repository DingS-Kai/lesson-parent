package com.fosu.lesson.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.TTime;
import com.fosu.lesson.service.TimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/time")
@Api(tags = "时间表的增删改查")
public class TimeController {

    @Reference
    private TimeService timeService;


    @ApiOperation("返回周一到周日")
    @GetMapping("/findDinstinctDay")
    public List<String> findDinstinctDay(){
        return this.timeService.findDayPerWeek();
    }

    @ApiOperation("/根据传入的周一到周日，返回当天的时间点（第一节到第七节）")
    @ApiImplicitParam(name="day",value = "传入的周一到周日:['mon','tue','wed','thu','fri','sta','sun']",
            dataType = "Stirng", paramType = "query", required = false)
    @GetMapping("/findTimeByDay")
    public List<TTime> findTimeByDay(@RequestParam String day){
        return this.timeService.findTimeByDay(day);
    }


}
