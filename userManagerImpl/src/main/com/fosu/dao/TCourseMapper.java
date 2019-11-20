package com.fosu.dao;

import com.fosu.pojo.TCourse;
import com.fosu.pojo.TCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCourseMapper {
    long countByExample(TCourseExample example);

    int deleteByExample(TCourseExample example);

    int insert(TCourse record);

    int insertSelective(TCourse record);

    List<TCourse> selectByExample(TCourseExample example);

    int updateByExampleSelective(@Param("record") TCourse record, @Param("example") TCourseExample example);

    int updateByExample(@Param("record") TCourse record, @Param("example") TCourseExample example);
}