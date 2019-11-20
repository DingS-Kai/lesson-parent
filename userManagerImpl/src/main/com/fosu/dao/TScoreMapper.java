package com.fosu.dao;

import com.fosu.pojo.TScore;
import com.fosu.pojo.TScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TScoreMapper {
    long countByExample(TScoreExample example);

    int deleteByExample(TScoreExample example);

    int insert(TScore record);

    int insertSelective(TScore record);

    List<TScore> selectByExample(TScoreExample example);

    int updateByExampleSelective(@Param("record") TScore record, @Param("example") TScoreExample example);

    int updateByExample(@Param("record") TScore record, @Param("example") TScoreExample example);
}