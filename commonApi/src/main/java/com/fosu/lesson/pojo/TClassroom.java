package com.fosu.lesson.pojo;

import java.io.Serializable;

public class TClassroom implements Serializable {
    private String classroomId;

    private String place;

    private String classroomNum;

    private String remark;

    public String getClassroomId() {
        return classroomId;
    }

    public String getPlace() {
        return place;
    }

    public String getClassroomNum() {
        return classroomNum;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId == null ? null : classroomId.trim();
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public void setClassroomNum(String classroomNum) {
        this.classroomNum = classroomNum == null ? null : classroomNum.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "classroomId:"+classroomId+"  ,place:"+place+"  ,classroomNum:"+classroomNum;
    }
}