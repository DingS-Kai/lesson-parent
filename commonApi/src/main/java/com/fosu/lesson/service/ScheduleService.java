package com.fosu.lesson.service;

import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.pojo.TSchedule;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    //查一个班的排课
    List<TSchedule> findOne(TSchedule tSchedule);

    //查全部班的排课
    Map<String, List<TSchedule>> findAll();

    //增加
    void save(TSchedule tSchedule);

    //更新
    void update(TSchedule tSchedule);

    //删除
    void delete(String[] ids);

    //分页
    PageResult findByPage(int pageNo, int pageSize);

}
