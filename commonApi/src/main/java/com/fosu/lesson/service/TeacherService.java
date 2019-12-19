package com.fosu.lesson.service;

import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TClassroom;
import com.fosu.lesson.pojo.TTeacher;

import java.util.List;

public interface TeacherService {
    //查一个
    TTeacher findOne(TTeacher tTeacher);

    //查全部
    List<TTeacher> findAll();

    //增加
    void save(TTeacher tTeacher);

    //更新
    void update(TTeacher tTeacher);

    //删除
    void delete(String[] ids);

    //分页
    PageResult findByPage(int pageNo, int pageSize);

    List<String> selectByColumnName(String teacher_name);
}
