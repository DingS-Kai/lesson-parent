package com.fosu.lesson.pojo;

import java.io.Serializable;

public class TScore implements Serializable {
    private String studentId;

    private String classId;

    private String courseId;

    private String score;

    @Override
    public String toString() {
        return "studentId: "+studentId+" ,classId:"+classId+",courseId:"+courseId
                +" ,score:"+score;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }
}