package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TScheduleMapper;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.pojo.TScheduleExample;
import com.fosu.lesson.service.ScheduleService;
import com.fosu.lesson.utils.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private TScheduleMapper tScheduleMapper;

    @Autowired
    private ScheduleUtils scheduleUtils;



    @Override
    public List<TSchedule> findOne(TSchedule tSchedule) {
        TScheduleExample tScheduleExample = new TScheduleExample();
        tScheduleExample.createCriteria().andClassIdEqualTo("102");
        List<TSchedule> list= tScheduleMapper.selectByExample(tScheduleExample);
        System.out.println("===================================================");
        for (TSchedule t:list){
            System.out.println(t.toString());
        }
        System.out.println("===================================================");
        return list;
    }

    @Override
    public List<TSchedule> findAll() {
        return null;
    }

    @Override
    public void save(TSchedule tSchedule) {

    }

    @Override
    public void update(TSchedule tSchedule) {

    }

    @Override
    public void delete(String[] ids) {

    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<TSchedule> qryOne() {
        return null;
    }

    @Override
    public String say() {
        return null;
    }
}
