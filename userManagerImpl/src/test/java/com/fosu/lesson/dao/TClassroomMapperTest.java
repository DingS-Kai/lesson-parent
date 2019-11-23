package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.fosu.lesson.utils.CreatName;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TClassroomMapperTest {

    @Autowired
    private  TScheduleMapper tScheduleMapper;

    @Test
    public void testSchdule(){

        TScheduleExample tScheduleExample = new TScheduleExample();
        tScheduleExample.createCriteria().andClassIdEqualTo("101");
        List<TSchedule> list= tScheduleMapper.selectByExample(tScheduleExample);
        System.out.println("===================================================");
        for (TSchedule t:list){
            System.out.println(t.toString());
        }
        System.out.println("===================================================");
    }



    @Autowired
    private TClassroomMapper tClassroomMapper;
    @Autowired
    private  TTimeMapper tTimeMapper;

    @Test
    public void Timetest(){
        TTime tTime = new TTime();
        int h=1;
        String []str1 = {"一","二","三","四","五","六","七"};
        String str[] = new String[]{"mon","tue","wed","thu","fri","sta","sun"};
        for (int i = 1; i <=20 ; i++) {
            for (int j = 0; j <7 ; j++) {
                for (int k = 0; k <7 ; k++) {
                    tTime.setTimeId(""+(h++));
                    tTime.setWeek(i+"");
                    tTime.setDay(str[j]);
                    tTime.setHour("第"+str1[k]+"节");
                    tTimeMapper.insert(tTime);
                }
            }
        }
    }

    @Test
    public void insert() {
        String []str = {"A","B","C"};
        TClassroom tClassroom = new TClassroom();
       // tClassroom.setTotalNum(40+"")
        tClassroom.setClassroomNum("40");
        int h=1;
        for (int i = 0; i <= 2; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 1; k <6 ; k++) {
                    tClassroom.setClassroomId(""+h++);
                    tClassroom.setPlace(str[i]+(j*100+k));
                    tClassroomMapper.insert(tClassroom);
                }
            }
        }
    }



    @Autowired
    private TTeacherMapper tTeacherMapper;
    @Test
    public void teacherInsert() {
        TTeacher tTeacher = new TTeacher();
        int h=20160301;
        for (int i = 1; i <= 9 ; i++) {
            tTeacher.setTeacherId(""+(h++));
            Map map= CreatName.getAddress();
            tTeacher.setTeacherName((String) map.get("name"));
            tTeacher.setEmail((String)map.get("email"));
            tTeacher.setTelephoto((String)map.get("tel"));
            tTeacher.setTeacherPw("123456");
            tTeacherMapper.insert(tTeacher);
        }
    }


    @Autowired
    private TStudentMapper tStudentMapper;
    @Test
    public void studentInsert() {
        TStudent tStudent = new TStudent();
        int h=20160501;
        for (int i = 1; i <= 40 ; i++) {
            tStudent.setStudentId(""+(h++));
            Map map= CreatName.getAddress();
            tStudent.setStudentName((String) map.get("name"));
            tStudent.setEmail((String)map.get("email"));
            tStudent.setTelephoto((String)map.get("tel"));
            tStudent.setStudentPw("123456");
            tStudent.setClassId("101");
            tStudentMapper.insert(tStudent);
        }
    }


    @Autowired
    private TCourseMapper tCourseMapper;
    @Test
    public void courseInsert() {

        String str[] = new String[]{"语文","数学","英语","物理","化学","生物","政治","地理","历史","音乐","体育"};
        TCourse tCourse = new TCourse();
        int h=1;
        int  q=20160301;
        for (int i = 1; i <=11 ; i++) {
            tCourse.setCourseId(""+(h++));
            tCourse.setClassId("101");
            tCourse.setTeacherId(""+(q++));
            tCourse.setClassHour("96");
            tCourse.setCourseName(str[i-1]);
            tCourse.setCourseSort("必修");
            tCourseMapper.insert(tCourse);
        }
    }



    @Autowired
    private TFreeMapper tFreeMapper;
    @Test
    public void freeInsert() {
        TFree tFree = new TFree();
        tFree.setFree("00");
        for (int i = 1; i <= 980 ; i++) {
            for (int j = 1; j <= 60; j++) {
                tFree.setTimeId(""+i);
                tFree.setClassroomId(""+j);
                tFreeMapper.insert(tFree);
            }
        }
    }




}
