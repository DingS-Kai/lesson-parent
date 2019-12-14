package com.fosu.lesson.pojo;

import java.io.Serializable;

public class TTeacher implements Serializable {
    private String teacherId;

    private String teacherName;

    private String telephoto;

    private String email;

    private String remark;

    private String teacherPw;

    @Override
    public String toString() {
        return "teacherId:"+teacherId+"  ,teacherName:"+teacherName+"  ,telephoto:"+telephoto+
                "  ,email:"+email+"  ,remark:"+remark;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTeacherPw() {
        return teacherPw;
    }

    public void setTeacherPw(String teacherPw) {
        this.teacherPw = teacherPw == null ? null : teacherPw.trim();
    }
}