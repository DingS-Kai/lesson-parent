package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TFree;
import com.fosu.lesson.pojo.TFreeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TFreeMapper {
    long countByExample(TFreeExample example);

    int deleteByExample(TFreeExample example);

    int insert(TFree record);

    int insertSelective(TFree record);

    List<TFree> selectByExample(TFreeExample example);

    int updateByExampleSelective(@Param("record") TFree record, @Param("example") TFreeExample example);

    int updateByExample(@Param("record") TFree record, @Param("example") TFreeExample example);
}