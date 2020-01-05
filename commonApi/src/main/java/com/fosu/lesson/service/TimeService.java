package com.fosu.lesson.service;

import com.fosu.lesson.pojo.TTime;

import java.util.List;

public interface TimeService {

    //返回周一到周日
    public List<String> findDayPerWeek();

    //返回每天的不同时间点，传入的参数day为“mon，tue，wed，thu ,fri,sta,sun”
    public List<TTime> findTimeByDay(String day);

}
