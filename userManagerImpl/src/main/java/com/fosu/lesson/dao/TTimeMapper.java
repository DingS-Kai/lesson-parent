package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TTime;
import com.fosu.lesson.pojo.TTimeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TTimeMapper {
    long countByExample(TTimeExample example);

    int deleteByExample(TTimeExample example);

    int insert(TTime record);

    int insertSelective(TTime record);

    List<TTime> selectByExample(TTimeExample example);

    int updateByExampleSelective(@Param("record") TTime record, @Param("example") TTimeExample example);

    int updateByExample(@Param("record") TTime record, @Param("example") TTimeExample example);
}