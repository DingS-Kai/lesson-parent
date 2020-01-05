package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.CourseWithTeacher;
import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TCourseExample;
import com.fosu.lesson.pojo.TTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCourseMapper {
    long countByExample(TCourseExample example);

    int deleteByExample(TCourseExample example);

    int insert(TCourse record);

    int insertSelective(TCourse record);

    List<TCourse> selectByExample(TCourseExample example);

    int updateByExampleSelective(@Param("record") TCourse record, @Param("example") TCourseExample example);

    int updateByExample(@Param("record") TCourse record, @Param("example") TCourseExample example);

    TCourse selectOne(TCourse tCourse);

    List<String> selectByColumnName(@Param("columnName") String columnName);

    List<CourseWithTeacher> findCourseWithTeacherByCondition(CourseWithTeacher condition);

    List<CourseWithTeacher> findTeacherByCourseName(String courseName);

}