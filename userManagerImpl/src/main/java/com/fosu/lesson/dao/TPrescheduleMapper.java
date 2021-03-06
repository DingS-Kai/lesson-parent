package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TPreschedule;
import com.fosu.lesson.pojo.TPrescheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPrescheduleMapper {
    long countByExample(TPrescheduleExample example);

    int deleteByExample(TPrescheduleExample example);

    int insert(TPreschedule record);

    int insertSelective(TPreschedule record);

    List<TPreschedule> selectByExample(TPrescheduleExample example);

    int updateByExampleSelective(@Param("record") TPreschedule record, @Param("example") TPrescheduleExample example);

    int updateByExample(@Param("record") TPreschedule record, @Param("example") TPrescheduleExample example);

    void deleteAll();
}