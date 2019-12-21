package com.fosu.lesson.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 封装班级id，和班级的所有课表
 *
 */
public class ClassSchedule implements Serializable {
    private String classId;
    private List<TSchedule> curriculum;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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
                "classId='" + classId + '\'' +
                ", curriculum=" + curriculum +
                '}';
    }
}
