package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.pojo.TScheduleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TScheduleMapper {
    long countByExample(TScheduleExample example);

    int deleteByExample(TScheduleExample example);

    int insert(TSchedule record);

    int insertSelective(TSchedule record);

    List<TSchedule> selectByExample(TScheduleExample example);

    int updateByExampleSelective(@Param("record") TSchedule record, @Param("example") TScheduleExample example);

    int updateByExample(@Param("record") TSchedule record, @Param("example") TScheduleExample example);

    TSchedule selectOne(TSchedule tSchedule);
}