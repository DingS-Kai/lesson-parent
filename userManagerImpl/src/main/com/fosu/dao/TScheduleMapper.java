package com.fosu.dao;

import com.fosu.pojo.TSchedule;
import com.fosu.pojo.TScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TScheduleMapper {
    long countByExample(TScheduleExample example);

    int deleteByExample(TScheduleExample example);

    int insert(TSchedule record);

    int insertSelective(TSchedule record);

    List<TSchedule> selectByExample(TScheduleExample example);

    int updateByExampleSelective(@Param("record") TSchedule record, @Param("example") TScheduleExample example);

    int updateByExample(@Param("record") TSchedule record, @Param("example") TScheduleExample example);
}