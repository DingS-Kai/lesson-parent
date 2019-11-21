package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TClassMapperTest {

    @Autowired
    private TClassMapper tClassMapper;

    @Test
    public void insert() {
/*
        String []str = {"一","二","三","四","五"};
        TClass tClass = new TClass();
        tClass.setTotalNum(40+"");
        int h=1;
        for (int i = 1; i <= 9; i++) {
                tClass.setGrade(i+"年级");
            for (int j = 1; j < 6; j++) {
                tClass.setClassName(str[j-1]+"班");
                tClass.setClassId(i+"0"+j);
                h++;
                tClassMapper.insert(tClass);
            }
            
        }*/
        System.out.println("=======================");
    }
}