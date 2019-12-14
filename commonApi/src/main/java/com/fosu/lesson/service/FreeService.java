package com.fosu.lesson.service;

import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TFree;
import com.fosu.lesson.pojo.TFree;

import java.util.List;

public interface FreeService {
    //查一个
    TFree findOne(TFree tFree);

    //查全部
    List<TFree> findAll();

    //增加
    void save(TFree tFree);

    //更新
    void update(TFree tFree);

    //删除
    void delete(List<TFree> list);

    //分页
    PageResult findByPage(int pageNo, int pageSize);
}
