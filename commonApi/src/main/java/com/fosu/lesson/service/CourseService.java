package com.fosu.lesson.service;

import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TTeacher;

import java.util.List;

public interface CourseService {
    //查一个
    TCourse findOne(TCourse tCourse);

    //查全部
    List<TCourse> findAll();

    //增加
    void save(TCourse tCourse);

    //更新
    void update(TCourse tCourse);

    //删除
    void delete(String[] ids);

    //分页
    PageResult findByPage(int pageNo, int pageSize);

    List<String> selectByColumnName(String class_id);
}
