package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TTimeMapper;
import com.fosu.lesson.pojo.TTime;
import com.fosu.lesson.pojo.TTimeExample;
import com.fosu.lesson.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    private TTimeMapper timeMapper;

    @Override
    public List<String> findDayPerWeek() {
        return timeMapper.findDistinctDay();
    }

    @Override
    public List<TTime> findTimeByDay(String day) {
        TTimeExample example = new TTimeExample();
        example.createCriteria().andWeekEqualTo("1").andDayEqualTo(day);
        example.setOrderByClause("time_id * 1");
        List<TTime> timeList = timeMapper.selectByExample(example);
        return timeList;
    }
}
