package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TFreeMapper;
import com.fosu.lesson.dao.TFreeMapper;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TFree;
import com.fosu.lesson.pojo.TFree;
import com.fosu.lesson.pojo.TFreeExample;
import com.fosu.lesson.service.FreeService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class FreeServiceImpl implements FreeService {
    
    @Autowired
    private TFreeMapper tFreeMapper;

    @Override
    public TFree findOne(TFree tFree) {
        TFree t = tFreeMapper.selectOne(tFree);
        System.out.println("service findOne========="+t.toString());
        return tFreeMapper.selectOne(tFree);
    }

    @Override
    public List<TFree> findAll() {
        return tFreeMapper.selectByExample(null);
    }

    @Override
    public void save(TFree tFree) {
        tFreeMapper.insertSelective(tFree);
    }

    @Override
    public void update(TFree tFree) {
        TFreeExample tFreeExample = new TFreeExample();
        TFreeExample.Criteria criteria = tFreeExample.createCriteria();
        criteria.andClassroomIdEqualTo(tFree.getClassroomId());
        criteria.andTimeIdEqualTo(tFree.getTimeId());
        tFreeMapper.updateByExample(tFree, tFreeExample);
    }

    @Override
    public void delete(List<TFree> list) {
        System.out.println("===================要delete的数据==============");
        Consumer consumer = (t) -> System.out.println(t);
        list.forEach(consumer);
        tFreeMapper.deleteList(list);
        System.out.println("===================delete========end======");
    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize) {
        PageResult pageResult = new PageResult();
        Page<TFree> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(
                new ISelect() {
                    @Override
                    public void doSelect() {
                        tFreeMapper.selectByExample(null);
                    }
                }
        );

        List<TFree> result = page.getResult();
        Consumer consumer = (item) -> System.out.println(item.toString());
        result.forEach(consumer);

        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }
}