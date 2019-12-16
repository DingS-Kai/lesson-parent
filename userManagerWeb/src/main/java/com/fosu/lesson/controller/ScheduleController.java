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
        List<TSchedule> list = scheduleService.findOne(new TSchedule());
        int flag=0;
        for (int i =1; i <=7; i++) {
            for(int j=0;j<5;j++){
                flag=0;
               for(TSchedule tSchedule:list){
                   if(tSchedule.getTimeId().equals((i+j*7)+"")){
                       System.out.printf(tSchedule.getCourseName()+"     ");
                       flag=1;
                   }
               }
               if(flag==0){
                   System.out.printf("空闲"+"     ");
               }
            }
            System.out.println("");
        }
        return list;
    }
    /**
     * TScheduleExample tScheduleExample = new TScheduleExample();
     *         tScheduleExample.createCriteria().andClassIdEqualTo("101");
     *         List<TSchedule> list= tScheduleMapper.selectByExample(tScheduleExample);
     *         System.out.println("===================================================");
     *         for (TSchedule t:list){
     *             System.out.println(t.toString());
     *         }
     *         System.out.println("===================================================");
     */
    @GetMapping("mymy")
    public void schedulePlan(){

         scheduleService.shcedule();
    }



}
