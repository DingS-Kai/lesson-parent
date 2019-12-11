package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TClassroomMapper;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TClassroom;
import com.fosu.lesson.pojo.TClassroomExample;
import com.fosu.lesson.service.ClassroomService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private TClassroomMapper tClassroomMapper;

    @Override
    public TClassroom findOne(TClassroom tClassroom) {
        return tClassroomMapper.selectOne(tClassroom);
    }

    @Override
    public List findAll() {
        return tClassroomMapper.selectByExample(null);
    }

    @Override
    public void save(TClassroom tClassroom) {
        tClassroomMapper.insertSelective(tClassroom);
    }

    @Override
    public void update(TClassroom tClassroom) {
        TClassroomExample tClassroomExample = new TClassroomExample();
        TClassroomExample.Criteria criteria = tClassroomExample.createCriteria();
        criteria.andClassroomIdEqualTo(tClassroom.getClassroomId());
        tClassroomMapper.updateByExampleSelective(tClassroom,tClassroomExample);
    }

    @Override
    public void delete(String[] ids) {
        TClassroomExample tClassroomExample = new TClassroomExample();
        TClassroomExample.Criteria criteria = tClassroomExample.createCriteria();
        List list = Arrays.asList(ids);
        criteria.andClassroomIdIn(list);
        tClassroomMapper.deleteByExample(tClassroomExample);
    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize) {
        PageResult pageResult = new PageResult();
        PageInfo<TClassroom> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(
                () -> tClassroomMapper.selectByExample(null));
        //PageHelper.startPage(pageNo,pageSize);
        //List list = tClassroomMapper.selectByExample(null);
        //PageInfo pageInfo = new PageInfo(list);
        List list = pageInfo.getList();
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);

        pageResult.setRows(pageInfo.getList());
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }
}