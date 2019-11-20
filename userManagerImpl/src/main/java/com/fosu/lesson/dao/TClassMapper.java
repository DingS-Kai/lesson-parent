package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TClass;
import com.fosu.lesson.pojo.TClassExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TClassMapper {
    long countByExample(TClassExample example);

    int deleteByExample(TClassExample example);

    int insert(TClass record);

    int insertSelective(TClass record);

    List<TClass> selectByExample(TClassExample example);

    int updateByExampleSelective(@Param("record") TClass record, @Param("example") TClassExample example);

    int updateByExample(@Param("record") TClass record, @Param("example") TClassExample example);
}