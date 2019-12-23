package com.fosu.lesson.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 封装班级id，和班级的所有课表
 *
 */
public class ClassSchedule implements Serializable {
    private TClass tClass;
    private List<TSchedule> curriculum;

    public TClass gettClass() {
        return tClass;
    }

    public void settClass(TClass tClass) {
        this.tClass = tClass;
    }

    public List<TSchedule> getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(List<TSchedule> curriculum) {
        this.curriculum = curriculum;
    }


    @Override
    public String toString() {
        return "ClassSchedule{" +
                "tClass=" + tClass +
                ", curriculum=" + curriculum +
                '}';
    }
}
