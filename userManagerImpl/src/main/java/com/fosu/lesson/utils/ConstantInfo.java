package com.fosu.lesson.utils;

public class ConstantInfo {
    public static final String CLASS_ID = "class_id";//班级编号
    public static final String COURSE_ID = "course_id";//课程编号
    public static final String TEACHER_ID = "teacher_id";//教师编号
    public static final String COURSE_NAME = "course_name";//课程编号
    public static final String COURSE_SORT = "course_sort";//课程属性
    public static final String CLASS_HOUR = "class_hour";//一周上课次数
    public static final String CLASS_TIME = "class_time";//上课时间
    public static final String PROFESSIONAL_CODE = "1";//专业课码值
    public static final String ELECTIVE_CODE = "2";//非专业课码值
    public static final String PHYSICAL_CODE = "3";//娱乐课码值

    public static double TIMENUM = 35;//时间片总数

    public static double K1 = 0.3;//专业课所占权重
    public static double K2 = 0.2;//非专业课所占权重
    public static double K3 = 0.2;//娱乐课所占权重
    public static double K4 = 0.2;//空闲时间权重
    public static double K5 = 0.2;//课程离散程度所占权重
    public static double K6 = 0.2;//一天顶多两门相同课的权重

    //期望值，数字越大，期望越高
    public static int F1 = -20;
    public static int F2 = -10;
    public static int F4 = 0;
    public static int F5 = 2;
    public static int F6 = 4;
    public static int F7 = 6;
    public static int F8 = 8;
    public static int F9 = 10;



    public static final int GENERATION = 1000;//遗传代数

}
