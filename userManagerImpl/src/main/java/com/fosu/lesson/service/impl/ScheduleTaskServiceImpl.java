package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TScheduletaskMapper;
import com.fosu.lesson.pojo.TScheduletask;
import com.fosu.lesson.service.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    @Autowired
    private TScheduletaskMapper tScheduletaskMapper;

    @Override
    public List<TScheduletask> findAll() {
        return tScheduletaskMapper.selectByExample(null);
    }
}
