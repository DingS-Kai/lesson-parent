package com.fosu.dao;

import com.fosu.pojo.TTime;
import com.fosu.pojo.TTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTimeMapper {
    long countByExample(TTimeExample example);

    int deleteByExample(TTimeExample example);

    int insert(TTime record);

    int insertSelective(TTime record);

    List<TTime> selectByExample(TTimeExample example);

    int updateByExampleSelective(@Param("record") TTime record, @Param("example") TTimeExample example);

    int updateByExample(@Param("record") TTime record, @Param("example") TTimeExample example);
}