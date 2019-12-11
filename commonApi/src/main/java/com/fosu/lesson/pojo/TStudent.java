package com.fosu.lesson.pojo;

import java.io.Serializable;

public class TStudent implements Serializable {
    private String studentId;

    private String studentName;

    private String telephoto;

    private String email;

    private String classId;

    private String remark;

    private String studentPw;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getTelephoto() {
        return telephoto;
    }

    public void setTelephoto(String telephoto) {
        this.telephoto = telephoto == null ? null : telephoto.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStudentPw() {
        return studentPw;
    }

    public void setStudentPw(String studentPw) {
        this.studentPw = studentPw == null ? null : studentPw.trim();
    }
}