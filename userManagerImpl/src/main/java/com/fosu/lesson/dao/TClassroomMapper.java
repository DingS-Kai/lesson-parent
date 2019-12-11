package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TClassroom;
import com.fosu.lesson.pojo.TClassroomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TClassroomMapper {
    long countByExample(TClassroomExample example);

    int deleteByExample(TClassroomExample example);

    int insert(TClassroom record);

    int insertSelective(TClassroom record);

    List<TClassroom> selectByExample(TClassroomExample example);

    int updateByExampleSelective(@Param("record") TClassroom record, @Param("example") TClassroomExample example);

    int updateByExample(@Param("record") TClassroom record, @Param("example") TClassroomExample example);

    TClassroom selectOne(TClassroom tClassroom);
}