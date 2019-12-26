package com.fosu.lesson.pojo;

import java.io.Serializable;

public class TCourse implements Serializable {
    private String grade;

    private String courseId;

    private String classId;

    private String teacherId;

    private String courseName;

    private String courseSort;

    private String classHour;

    private String remark;

    private String free;

    private String teachertime;

    @Override
    public String toString() {
        return "courseId: "+courseId+" ,classId:"+classId+",teacherId:"+teacherId+" ,courseName:"+courseName+",courseSort:"+courseSort+" ,classHour:"+classHour+",remark:"+remark;
    }
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseSort() {
        return courseSort;
    }

    public void setCourseSort(String courseSort) {
        this.courseSort = courseSort == null ? null : courseSort.trim();
    }

    public String getClassHour() {
        return classHour;
    }

    public void setClassHour(String classHour) {
        this.classHour = classHour == null ? null : classHour.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free == null ? null : free.trim();
    }

    public String getTeachertime() {
        return teachertime;
    }

    public void setTeachertime(String teachertime) {
        this.teachertime = teachertime == null ? null : teachertime.trim();
    }
}