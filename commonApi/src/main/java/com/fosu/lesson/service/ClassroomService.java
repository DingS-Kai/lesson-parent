package com.fosu.lesson.service;

import com.fosu.lesson.pojo.TClassroom;
import com.fosu.lesson.pojo.PageResult;

import java.util.List;

public interface ClassroomService {

    //查一个
    TClassroom findOne(TClassroom tClassroom);

    //查全部
    List<TClassroom> findAll();

    //增加
    void save(TClassroom tClassroom);

    //更新
    void update(TClassroom tClassroom);

    //删除
    void delete(String[] ids);

    //分页
    PageResult findByPage(int pageNo, int pageSize);
}
