package com.fosu.lesson.service;

import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TPreschedule;

import java.util.List;

public interface PreScheduleService {

    //查一个
    TPreschedule findOne(Integer id);

    //查全部
    List<TPreschedule> findAll(String classId);

    //增加
    void save(TPreschedule tPreschedule);

    //更新
    void update(TPreschedule tPreschedule);

    //删除
    void delete(Integer[] ids);

    //分页
    PageResult findByPage(int pageNo, int pageSize, String classId);

}
