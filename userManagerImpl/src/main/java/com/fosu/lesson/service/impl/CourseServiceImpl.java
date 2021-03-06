package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TCourseMapper;
import com.fosu.lesson.pojo.*;
import com.fosu.lesson.service.CourseService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private TCourseMapper tCourseMapper;

    @Override
    public TCourse findOne(TCourse tCourse) {
        return tCourseMapper.selectOne(tCourse);
    }

    @Override
    public List<TCourse> findAll() {
        return tCourseMapper.selectByExample(null);
    }

    @Override
    public void save(TCourse tCourse) {
        tCourseMapper.insertSelective(tCourse);
    }

    @Override
    public void update(TCourse tCourse) {
        TCourseExample tCourseExample = new TCourseExample();
        TCourseExample.Criteria criteria = tCourseExample.createCriteria();
        criteria.andCourseIdEqualTo(tCourse.getCourseId());
        tCourseMapper.updateByExampleSelective(tCourse, tCourseExample);
    }

    @Override
    public void delete(String[] ids) {
        TCourseExample tCourseExample = new TCourseExample();
        TCourseExample.Criteria criteria = tCourseExample.createCriteria();

        criteria.andCourseIdIn(Arrays.asList(ids));
        tCourseMapper.deleteByExample(tCourseExample);
    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize) {
        PageResult pageResult = new PageResult();
        Page<TStudent> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(
                new ISelect() {
                    @Override
                    public void doSelect() {
                        tCourseMapper.selectByExample(null);
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

    @Override
    public List<String> selectByColumnName(String class_id) {
        return tCourseMapper.selectByColumnName(class_id);
    }

    @Override
    public List<CourseWithTeacher> findCourseWithTeacherByCondition(CourseWithTeacher condition) {
        return this.tCourseMapper.findCourseWithTeacherByCondition(condition);
    }

    @Override
    public List<CourseWithTeacher> findTeacherByCourseName(String courseName) {
        return this.tCourseMapper.findTeacherByCourseName(courseName);
    }

    @Override
    public List<String> findDistinctCourseNameByClassId(String classId) {
        return this.tCourseMapper.findDistinctCourseNameByClassId(classId);
    }

    @Override
    public List<TTeacher> findTeacherByCourse(TCourse course) {
        return this.tCourseMapper.findTeacherByCourse(course);
    }

}