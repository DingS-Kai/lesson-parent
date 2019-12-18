package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TTeacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建数据
 *
 * @author lhz
 * @date 2019/12/18
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateData {

    @Autowired
    private TCourseMapper tCourseMapper;

    @Autowired
    private TTeacherMapper tTeacherMapper;

    int k = 1;

    public List<String> getTeacherIdList(String[] str) {

        TTeacher tTeacher = new TTeacher();
        //装老师id
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            if ("a".equals(str[i])){
                list.add("a");
                continue;
            }
            tTeacher.setTeacherName(str[i]);
            TTeacher tTeacher1 = tTeacherMapper.selectOne(tTeacher);
            System.out.println(tTeacher1.toString());
            list.add(tTeacherMapper.selectOne(tTeacher).getTeacherId());
        }
        return list;
    }

    @Test
    public void createCourse(){

        String[] remark = {"语文","数学","英语","思品","物理","化学","历史","地理","生物","体育","音乐","美术","综实1","综实2","阅读","研究性学习","班会"};
        String[] courseName = {"01","02","03",  "07" ,"04"  ,"05"  ,"08" ,"09"  ,"06" ,"10"  ,"11"  ,"12"  ,"13"   ,"14"  ,"15"  ,"16"       ,"17" };
        String[] courseSort = {"1","1"  ,"1"  ,"2"   ,"2"   ,"2"   ,"2"  ,"2"   ,"2"  ,"3"   ,"3"   ,"3"   ,"3"    ,"3"   ,"3"   ,"3"        ,"3"};

        String[] classHour7 = {"5" ,"5"  ,"5"  ,"2"   ,"0"   ,"0"   ,"2"  ,"2"   ,"3"  ,"3"   ,"1"   ,"1"   ,"2"    ,"1"   ,"0"   ,"1"        ,"1"};
        String[] classHour8 = {"5" ,"5"  ,"5"  ,"2"   ,"3"   ,"0"  ,"2"  ,"2"    ,"2"  ,"3"   ,"1"   ,"1"   ,"1"    ,"1"   ,"0"   ,"0"        ,"1"};
        String[] classHour9 = {"5" ,"5"  ,"5"  ,"2"   ,"4"   ,"4"  ,"2"  ,"0"    ,"0"  ,"3"   ,"1"   ,"1"   ,"0"    ,"1"   ,"1"   ,"1"        ,"1"};

     
        String[] teacherName701 = {"马冬晓","陈岩","陈红星","钱萍","a","a","王金保","顾哲羽","王育忠","李子鹏","钱萍","陈丽娟","沈芝蕊","秦伟","a","周俊才","陈红星"};
        String[] teacherName702 = {"马冬晓","彭依宁","陈如","钱萍","a","a","王金保","顾哲羽","王育忠","徐丽娇","钱萍","陈丽娟","沈芝蕊","秦伟","a","周俊才","徐丽娇"};
        String[] teacherName703 = {"朱玲","彭依宁","沈芝蕊","王凤娟","a","a","王金保","顾哲羽","王育忠","李子鹏","钱萍","陈丽娟","聂广荣","秦伟","a","周俊才","彭依宁"};
        String[] teacherName704 = {"杨琳林","朱薇","顾哲羽","王凤娟","a","a","王金保","陈如","许亚","李子鹏","钱萍","陆海燕","聂广荣","袁宝利","a","周俊才","陆海燕"};
        String[] teacherName705 = {"杨琳林","陈岩","陈红星","王凤娟","a","a","王金保","陈如","许亚","刘琦","钱萍","陈丽娟","聂广荣","袁宝利","a","周俊才","刘琦"};
        String[] teacherName706 = {"朱玲","王丰","钱燕","缪新华","a","a","王娟","邵海英","许亚","刘琦","钱萍","陈丽娟","沈芝蕊","袁宝利","a","周俊才","钱燕"};
        String[] teacherName707 = {"丁春娟","朱薇","钱燕","缪新华","a","a","王娟","邵海英","许亚","刘琦","钱萍","陈丽娟","聂广荣","袁宝利","a","周俊才","朱薇"};
        String[] teacherName801 = {"耿金华","陈丽黎","侯沅村","缪新华","袁宝利","a","王娟","赵秀梅","张颖","李忠健","景珠","陆海燕","聂广荣","张耀忠","a","a","李忠健"};
        String[] teacherName802 = {"黄永平","王育红","蔡瑛敏","缪新华","袁宝利","a","王娟","曹佳蕾","张颖","李子鹏","景珠","陆海燕","聂广荣","张耀忠","a","a","蔡瑛敏"};
        String[] teacherName803 = {"黄芳","王国英","曹佳蕾","缪新华","秦伟","a","王娟","赵秀梅","张颖","李子鹏","景珠","陆海燕","聂广荣","张耀忠","a","a","张颖"};
        String[] teacherName804 = {"耿金华","王国英","徐春芳","缪新华","秦伟","a","徐臻","曹佳蕾","张颖","徐丽娇","景珠","陆海燕","聂广荣","张耀忠","a","a","徐秦"};
        String[] teacherName805 = {"赵秀梅","陈雪芬","侯沅村","孙颖萍","李新艳","a","王娟","曹佳蕾","张颖","徐丽娇","景珠","陆海燕","聂广荣","张耀忠","a","a","孙颖萍"};
        String[] teacherName806 = {"黄芳","陈丽黎","蔡瑛敏","孙颖萍","李新艳","a","徐臻","邵海英","张颖","徐丽娇","景珠","陆海燕","聂广荣","张耀忠","a","a","陈丽黎"};
        String[] teacherName807 = {"黄永平","陈雪芬","徐春芳","孙颖萍","李新艳","a","徐臻","邵海英","张颖","徐丽娇","景珠","陆海燕","聂广荣","张耀忠","a","a","徐春芳"};
        String[] teacherName901 = {"李艳","陆建东","王慧","孙颖萍","蒋虎","陆轶华","耿海燕","a","a","李忠健","景珠","陆海燕","a","张耀忠","王慧","a","陆轶华"};
        String[] teacherName902 = {"奚丽娅","张国华","许卫兵","徐臻","蒋虎","陆轶华","耿海燕","a","a","李忠健","景珠","陆海燕","a","张耀忠","许卫兵","a","张国华"};
        String[] teacherName903 = {"陆金花","陆建东","王慧","孙颖萍","蒋虎","周健","耿海燕","a","a","刘琦","景珠","陆海燕","a","周俊才","王慧","a","周健"};
        String[] teacherName904 = {"李艳","陈爱琴","陈娅","徐臻","张海英","任世忠","耿海燕","a","a","刘琦","景珠","陆海燕","a","周俊才","陈娅","a","耿海燕"};
        String[] teacherName905 = {"奚丽娅","陈爱琴","许卫兵","孙颖萍","张海英","周健","耿海燕","a","a","彭建德","景珠","陆海燕","a","周俊才","许卫兵","a","许卫兵"};
        String[] teacherName906 = {"陆金花","张国华","陈娅","徐臻","张海英","邹汉江","耿海燕","a","a","彭建德","景珠","陆海燕","a","周俊才","陈娅","a","陈娅"};

        List<String[]> teacherName = new ArrayList();
        teacherName.add(teacherName701);
        teacherName.add(teacherName702);
        teacherName.add(teacherName703);
        teacherName.add(teacherName704);
        teacherName.add(teacherName705);
        teacherName.add(teacherName706);
        teacherName.add(teacherName707);
        teacherName.add(teacherName801);
        teacherName.add(teacherName802);
        teacherName.add(teacherName803);
        teacherName.add(teacherName804);
        teacherName.add(teacherName805);
        teacherName.add(teacherName806);
        teacherName.add(teacherName807);
        teacherName.add(teacherName901);
        teacherName.add(teacherName902);
        teacherName.add(teacherName903);
        teacherName.add(teacherName904);
        teacherName.add(teacherName905);
        teacherName.add(teacherName906);

        String[] classHour = null;
        int t = 0;
        for (int i = 0; i < 3; i++) {
            classHour = classHour7;
            if (1 == i)
                classHour = classHour8;
            if (2 == i)
                classHour = classHour9;

            for (int j = 0; j < 7; j++) {
                String classId = (7+i)+"0";
                classId = classId + (j+1);
                if (i == 2 && j == 6)
                    break;
                insertCourse(remark, courseName, courseSort, classHour, teacherName.get(t), Integer.parseInt(classId));
                t++;
            }
        }

    }

    private void insertCourse(String[] remark, String[] courseName, String[] courseSort, String[] classHour7, String[] teacherName701, int classId) {

        List<String> list = getTeacherIdList(teacherName701);
        TCourse tCourse = new TCourse();
        for (int i = 0; i < list.size(); i++) {
            if ("a".equals(list.get(i)))
                continue;
            k++;
            tCourse.setCourseId(k+"");
            tCourse.setClassId(classId+"");
            tCourse.setTeacherId(list.get(i));
            tCourse.setRemark(remark[i]);
            tCourse.setCourseName(courseName[i]);
            tCourse.setCourseSort(courseSort[i]);
            tCourse.setClassHour(classHour7[i]);

            System.out.println(tCourse.toString());
            tCourseMapper.insert(tCourse);
        }
    }




    @Test
    public void test(){
        String[] teacherName701 = {"马冬晓","陈岩","陈红星","钱萍","a","a","王金保","顾哲羽","王育忠","李子鹏","钱萍","陈丽娟","沈芝蕊","秦伟","a","周俊才","陈红星"};
        getTeacherIdList(teacherName701);
    }
}