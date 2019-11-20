package com.fosu.dao;

import com.fosu.pojo.TTeacher;
import com.fosu.pojo.TTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTeacherMapper {
    long countByExample(TTeacherExample example);

    int deleteByExample(TTeacherExample example);

    int insert(TTeacher record);

    int insertSelective(TTeacher record);

    List<TTeacher> selectByExample(TTeacherExample example);

    int updateByExampleSelective(@Param("record") TTeacher record, @Param("example") TTeacherExample example);

    int updateByExample(@Param("record") TTeacher record, @Param("example") TTeacherExample example);
}