package com.fosu.lesson.service;

import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TStudent;
import com.fosu.lesson.pojo.TTeacher;

import java.util.List;

public interface StudentService {
    //查一个
    TStudent findOne(TStudent tStudent);

    //查全部
    List<TStudent> findAll();

    //增加
    void save(TStudent tStudent);

    //更新
    void update(TStudent tStudent);

    //删除
    void delete(String[] ids);

    //分页
    PageResult findByPage(int pageNo, int pageSize);
}
