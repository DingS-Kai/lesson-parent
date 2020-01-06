package com.fosu.lesson.service;

import com.fosu.lesson.pojo.CourseWithTeacher;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TTeacher;

import java.util.List;

public interface CourseService {
    //查一个
    TCourse findOne(TCourse tCourse);

    //查全部
    List<TCourse> findAll();

    //增加
    void save(TCourse tCourse);

    //更新
    void update(TCourse tCourse);

    //删除
    void delete(String[] ids);

    //分页
    PageResult findByPage(int pageNo, int pageSize);

    List<String> selectByColumnName(String class_id);

    List<CourseWithTeacher> findCourseWithTeacherByCondition(CourseWithTeacher condition);

    List<CourseWithTeacher> findTeacherByCourseName(String courseName);

    //通过classId查询该班级所开设的课程
    List<String> findDistinctCourseNameByClassId(String classId);

    //通过传入的course，course封装了classId和课程名的条件，  查询改班级指定课程的所有授课老师
    List<TTeacher> findTeacherByCourse(TCourse course);

}
