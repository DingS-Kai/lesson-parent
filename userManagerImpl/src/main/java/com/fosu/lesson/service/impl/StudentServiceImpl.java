package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TStudentMapper;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TStudent;
import com.fosu.lesson.pojo.TStudentExample;
import com.fosu.lesson.service.StudentService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private TStudentMapper tStudentMapper;

    @Override
    public TStudent findOne(TStudent tStudent) {
        return tStudentMapper.selectOne(tStudent);
    }

    @Override
    public List<TStudent> findAll() {
        return tStudentMapper.selectByExample(null);
    }

    @Override
    public void save(TStudent tStudent) {
        tStudentMapper.insertSelective(tStudent);
    }

    @Override
    public void update(TStudent tStudent) {
        TStudentExample tStudentExample = new TStudentExample();
        TStudentExample.Criteria criteria = tStudentExample.createCriteria();
        criteria.andStudentIdEqualTo(tStudent.getStudentId());
        tStudentMapper.updateByExampleSelective(tStudent, tStudentExample);
    }

    @Override
    public void delete(String[] ids) {
        TStudentExample tStudentExample = new TStudentExample();
        TStudentExample.Criteria criteria = tStudentExample.createCriteria();

        criteria.andStudentIdIn(Arrays.asList(ids));
        tStudentMapper.deleteByExample(tStudentExample);
    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize) {
        PageResult pageResult = new PageResult();
        Page<TStudent> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(
                new ISelect() {
                    @Override
                    public void doSelect() {
                        tStudentMapper.selectByExample(null);
                    }
                }
        );

        List<TStudent> result = page.getResult();
        Consumer consumer = (item) -> System.out.println(item);
        result.forEach(consumer);

        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }
}