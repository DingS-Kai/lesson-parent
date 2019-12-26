package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TTeacherMapper;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TTeacher;
import com.fosu.lesson.pojo.TTeacherExample;
import com.fosu.lesson.service.TeacherService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TTeacherMapper tTeacherMapper;

    @Override
    public TTeacher findOne(TTeacher tTeacher) {
        return tTeacherMapper.selectOne(tTeacher);
    }

    @Override
    public List<TTeacher> findAll() {
        return tTeacherMapper.selectByExample(null);
    }

    @Override
    public void save(TTeacher tTeacher) {
        tTeacherMapper.insertSelective(tTeacher);
    }

    @Override
    public void update(TTeacher tTeacher) {
        TTeacherExample tTeacherExample = new TTeacherExample();
        TTeacherExample.Criteria criteria = tTeacherExample.createCriteria();
        criteria.andTeacherIdEqualTo(tTeacher.getTeacherId());
        tTeacherMapper.updateByExample(tTeacher, tTeacherExample);
    }

    @Override
    public void delete(String[] ids) {
        TTeacherExample tTeacherExample = new TTeacherExample();
        TTeacherExample.Criteria criteria = tTeacherExample.createCriteria();

        criteria.andTeacherIdIn(Arrays.asList(ids));
        tTeacherMapper.deleteByExample(tTeacherExample);
    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize) {
        PageResult pageResult = new PageResult();
        Page<TTeacher> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(
                new ISelect() {
                    @Override
                    public void doSelect() {
                        tTeacherMapper.selectByExample(null);
                    }
                }
        );

        List<TTeacher> result = page.getResult();
        Consumer consumer = (item) -> System.out.println(item.toString());
        result.forEach(consumer);

        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    @Override
    public List<String> selectByColumnName(String teacher_name) {
        return tTeacherMapper.selectByColumnName(teacher_name);
    }

    @Override
    public TTeacher findTeacher(TCourse tCourse) {
        return tTeacherMapper.findTeacher(tCourse);
    }
}