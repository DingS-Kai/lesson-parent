package com.fosu.lesson.dao;

import com.fosu.lesson.utils.ScheduleUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulePlanTest {



    @Test
    public void schedule(){
        ScheduleUtils scheduleUtils = new ScheduleUtils();
         scheduleUtils.schedulePlan();
        System.out.println("111");
    }



}
