package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.pojo.TScheduleExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test2 {

    @Autowired
    private TScheduleMapper tScheduleMapper;

    @Test
    public void printClass(){

        TSchedule tSchedule = new TSchedule();
        tSchedule.setClassId("101");

        System.out.println(tSchedule.getClassId());
        TScheduleExample tScheduleExample = new TScheduleExample();

        tScheduleExample.createCriteria().andClassIdEqualTo(tSchedule.getClassId());

        System.out.println(tScheduleExample.getOredCriteria().toString());
        // tScheduleExample.createCriteria().andClassIdEqualTo("102");
        List<TSchedule> list = tScheduleMapper.selectByExample(tScheduleExample);

        System.out.println("===================================================");
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);
        System.out.println("===================================================");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(" "+list.get(i + (j * 7)));
            }
            System.out.println();
        }
    }

}