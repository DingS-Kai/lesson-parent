package com.fosu.dao;

import com.fosu.pojo.TClassroom;
import com.fosu.pojo.TClassroomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TClassroomMapper {
    long countByExample(TClassroomExample example);

    int deleteByExample(TClassroomExample example);

    int insert(TClassroom record);

    int insertSelective(TClassroom record);

    List<TClassroom> selectByExample(TClassroomExample example);

    int updateByExampleSelective(@Param("record") TClassroom record, @Param("example") TClassroomExample example);

    int updateByExample(@Param("record") TClassroom record, @Param("example") TClassroomExample example);
}