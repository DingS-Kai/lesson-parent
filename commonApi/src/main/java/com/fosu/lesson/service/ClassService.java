package com.fosu.lesson.service;

import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TClass;

import java.util.List;

public interface ClassService {
    //查一个
    TClass findOne(TClass tClass);

    //查全部
    List<TClass> findAll();

    //增加
    void save(TClass tClass);

    //更新
    void update(TClass tClass);

    //删除
    void delete(String[] ids);

    //分页
    PageResult findByPage(int pageNo, int pageSize);

    //查一个年级有多少个班
    List<String> findClass(String grade);

    //返回所有的年级
    List<String> findGrade();
}
