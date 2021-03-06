package com.fosu.lesson.pojo;

import java.io.Serializable;

public class TTime implements Serializable {
    private String timeId;

    private String week;

    private String day;

    private String hour;

    private String remark;

    @Override
    public String toString() {
        return "timeId:"+timeId+"  ,week:"+week+"  ,day:"+day+
                "  ,hour:"+hour+"  ,remark:"+remark;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId == null ? null : timeId.trim();
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour == null ? null : hour.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}