package com.fosu.lesson.service;

import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TScore;
import com.fosu.lesson.pojo.TTeacher;

import java.util.List;

public interface ScoreService {
    //查一个
    TScore findOne(TScore tScore);

    //查全部
    List<TScore> findAll();

    //增加
    void save(TScore tScore);

    //更新
    void update(TScore tScore);

    //删除
    void delete(String[] ids);

    //分页
    PageResult findByPage(int pageNo, int pageSize);
}
