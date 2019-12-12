package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TScore;
import com.fosu.lesson.pojo.TScoreExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TScoreMapper {
    long countByExample(TScoreExample example);

    int deleteByExample(TScoreExample example);

    int insert(TScore record);

    int insertSelective(TScore record);

    List<TScore> selectByExample(TScoreExample example);

    int updateByExampleSelective(@Param("record") TScore record, @Param("example") TScoreExample example);

    int updateByExample(@Param("record") TScore record, @Param("example") TScoreExample example);

    TScore selectOne(TScore tScore);
}