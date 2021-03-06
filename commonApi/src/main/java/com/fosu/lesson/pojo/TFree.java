package com.fosu.lesson.pojo;

import java.io.Serializable;

public class TFree implements Serializable {
    private String timeId;

    private String classroomId;

    private String free;

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId == null ? null : timeId.trim();
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId == null ? null : classroomId.trim();
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free == null ? null : free.trim();
    }

    @Override
    public String toString() {
        return "timeId: "+timeId+" ,classroomId:"+classroomId+",free:"+free;
    }
}