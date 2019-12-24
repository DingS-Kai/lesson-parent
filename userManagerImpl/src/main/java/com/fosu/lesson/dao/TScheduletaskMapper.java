package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TScheduletask;
import com.fosu.lesson.pojo.TScheduletaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TScheduletaskMapper {
    long countByExample(TScheduletaskExample example);

    int deleteByExample(TScheduletaskExample example);

    int insert(TScheduletask record);

    int insertSelective(TScheduletask record);

    List<TScheduletask> selectByExample(TScheduletaskExample example);

    int updateByExampleSelective(@Param("record") TScheduletask record, @Param("example") TScheduletaskExample example);

    int updateByExample(@Param("record") TScheduletask record, @Param("example") TScheduletaskExample example);
}