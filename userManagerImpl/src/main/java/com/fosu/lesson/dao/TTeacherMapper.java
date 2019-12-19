package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TTeacher;
import com.fosu.lesson.pojo.TTeacherExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TTeacherMapper {
    long countByExample(TTeacherExample example);

    int deleteByExample(TTeacherExample example);

    int insert(TTeacher record);

    int insertSelective(TTeacher record);

    List<TTeacher> selectByExample(TTeacherExample example);

    int updateByExampleSelective(@Param("record") TTeacher record, @Param("example") TTeacherExample example);

    int updateByExample(@Param("record") TTeacher record, @Param("example") TTeacherExample example);

    TTeacher selectOne(TTeacher tTeacher);
    // 查出所有的教师id
    List<String> selectByColumnName(@Param("teacher_name") String teacher_name);
}