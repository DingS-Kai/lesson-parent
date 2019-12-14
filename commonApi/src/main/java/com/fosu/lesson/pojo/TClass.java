package com.fosu.lesson.pojo;

import java.io.Serializable;

public class TClass implements Serializable {
    private String className;

    private String classId;

    private String totalNum;

    private String remark;

    private String grade;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum == null ? null : totalNum.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    @Override
    public String toString() {
        return "className: "+className+" ,classId:"+classId+",totalNum:"+totalNum+" ,grade:"+grade+",remark:"+remark;
    }
}