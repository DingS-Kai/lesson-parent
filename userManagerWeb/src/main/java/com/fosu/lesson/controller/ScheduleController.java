package com.fosu.lesson.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.fosu.lesson.pojo.*;
import com.fosu.lesson.service.CourseService;
import com.fosu.lesson.service.ScheduleService;
import com.fosu.lesson.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/schedule")
@Api(tags = "基本信息管理-排课")
public class ScheduleController {

    @Reference(retries = 0)
    private ScheduleService scheduleService;
    @Reference(retries = 0)
    private CourseService courseService;
    @Reference(retries = 0)
    private TeacherService teacherService;

    @ApiOperation(value = "查找班级排课信息后台测试用" )
    @GetMapping("/qryOne")
    public List<TSchedule> qryOne(){
        TSchedule tSchedule1 = new TSchedule();
        TSchedule tSchedule2 = new TSchedule();
        List<String> classNoList = courseService.selectByColumnName("class_id");
        List<String> teacherIdList = teacherService.selectByColumnName("teacher_name");
        for(String class_name:classNoList){
            tSchedule1.setClassId(class_name);
            List<TSchedule> list = scheduleService.findOne(tSchedule1,true);
            int flag=0;
            System.out.println("==================="+class_name+"班课表===================");
            for (int i =1; i <=7; i++) {
                for(int j=0;j<5;j++){
                    flag=0;
                    for(TSchedule tSchedule:list){
                        if(tSchedule.getTimeId().equals((i+j*7)+"")){
                            System.out.printf(tSchedule.getCourseName()+" "+tSchedule.getTeacherName()+"     ");
                            flag=1;
                        }
                    }
                    if(flag==0){
                        System.out.printf("空闲 放假中"+"     ");
                    }
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("================================================");
            System.out.println("");
        }
        for(String teacher :teacherIdList){
            tSchedule2.setTeacherName(teacher);
            List<TSchedule> list = scheduleService.findOne(tSchedule2,false);
            int flag=0;
            System.out.println("==================="+teacher+"课表===================");
            for (int i =1; i <=7; i++) {
                for(int j=0;j<5;j++){
                    flag=0;
                    for(TSchedule tSchedule:list){
                        if(tSchedule.getTimeId().equals((i+j*7)+"")){
                            System.out.printf(tSchedule.getClassName()+" "+tSchedule.getCourseName()+"     ");
                            flag=1;
                        }
                    }
                    if(flag==0){
                        System.out.printf("空闲   放假中"+"     ");
                    }
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("================================================");
            System.out.println("");
        }

        return null;
    }


//    @ApiOperation(value = "开始排课" )
//    @GetMapping("/schedule")
//    public void schedulePlan(){
//        List<TSchedule> tScheduleList=new ArrayList<>();
//        TSchedule t1 = new TSchedule();
//        t1.setCourseName("语文");
//        t1.setTeacherId("20160312");
//        t1.setClassId("701");
//        t1.setTimeId("8");
//
//        TSchedule t2 = new TSchedule();
//        t2.setCourseName("英语");
//        t2.setTeacherId("20160343");
//        t2.setClassId("903");
//        t2.setTimeId("8");
//        tScheduleList.add(t1);
//        tScheduleList.add(t2);
//            scheduleService.shcedule(tScheduleList,null);
//
//
//    }

    @ApiOperation(value = "开始排课" )
    @GetMapping("/scheduleCondition")
    public void schedulePlan(String grade){
        System.out.println("===debut===");
        System.out.println("grade="+grade);
        System.out.println("============");
        List<TSchedule> tScheduleList=new ArrayList<>();
        TSchedule t1 = new TSchedule();
        t1.setCourseName("语文");
        t1.setTeacherId("20160312");
        t1.setClassId("701");
        t1.setTimeId("8");

        TSchedule t2 = new TSchedule();
        t2.setCourseName("英语");
        t2.setTeacherId("20160343");
        t2.setClassId("903");
        t2.setTimeId("8");
        tScheduleList.add(t1);
        tScheduleList.add(t2);
        scheduleService.shcedule(tScheduleList,grade);


    }


    @ApiOperation(value = "单个老师的课表" )
    @ApiImplicitParam(name = "tercherId" , dataType = "String" ,value = "传入一个老师的id" ,required = true )
    @GetMapping("/teacherplan")
    public List<TSchedule> tercherPlan(String tercherId){
        return scheduleService.getOneTercherPlan(tercherId);
    }

    @ApiOperation(value = "单个学生的课表" )
    @ApiImplicitParam(name = "classId" ,dataType = "String" ,value = "传入班级classId" ,required = true )
    @GetMapping("/studentplan")
    public List<TSchedule> studentPlan(String classId){
         return scheduleService.getOneStudentPlan(classId);
    }

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查找" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo" , dataType = "int" ,value = "页码" ) ,
            @ApiImplicitParam(name = "pageSize" , dataType = "int" , value = "一页的条数")
    })
    public PageResult findByPage(int pageNo, int pageSize) {
        System.out.println(pageNo+"======="+pageSize);
        return scheduleService.findByPage(pageNo, pageSize);
    }

    @ApiOperation(value = "清空课表" )
    @ApiImplicitParam(name = "grade" , dataType = "String" ,value = "传入一个年级" ,required = false )
    @GetMapping("/clearSchedule")
    public void clearSchedule(String grade){
        scheduleService.clearSchedule(grade);
    }

    @PostMapping("/findOne")
    @ApiOperation(value = "查找一个班级排课信息--后台测试用" , notes = "通过班级排课的部分信息获取班级排课的完整信息")
    @ApiImplicitParam(name = "tSchedule" ,dataType = "TSchedule" , value = "班级排课的部分信息作为参数" ,required = true )
    public List<TSchedule> findOne(@RequestBody TSchedule tSchedule){
        return scheduleService.findOne(tSchedule,true);
    }

    @ApiOperation(value = "查找所有班级排课信息")
    @GetMapping("/findAll")
    public Map<String, List<TSchedule>> findAll(@RequestParam(required=false) String grade){
        return scheduleService.findAll(grade);
    }

    @ApiOperation(value = "查找所有班级排课信息*")
    @GetMapping("/findAllSchedule")
    public List<ClassSchedule> findAllSchedule(@RequestParam(required=false) String grade){
        return scheduleService.findAllSchedule(grade);
    }

    //增加
    @PostMapping("/save")
    @ApiOperation(value = "新增班级排课信息" )
    @ApiImplicitParam(name = "tSchedule" ,dataType = "TSchedule" , value = "新增的班级排课信息" ,required = true )
    public boolean save(@RequestBody TSchedule tSchedule){
        int flag = 1;
        try {
            scheduleService.save(tSchedule);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "删除班级排课信息" )
    @ApiImplicitParam(name = "ids" ,allowMultiple = true, dataType = "String" ,value = "删除班级排课信息" ,required = true )
    @GetMapping("/deleteByIds")
    public boolean del(Integer[] ids){
        System.out.println("===============controller=============");
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        System.out.println("===============controller===========end==");
        int flag = 1;
        try {
            scheduleService.delete(ids);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        System.out.println("flag====="+flag);
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "更新班级排课信息" )
    @ApiImplicitParam(name = "tSchedule" , dataType = "TSchedule" , value = "更新的班级排课信息" ,required = true )
    @PostMapping("/update")
    public boolean update(@RequestBody TSchedule tSchedule){
        int flag = 1;
        try {
            scheduleService.update(tSchedule);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }

    /**
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    @ApiOperation(value = "下载单个学生的课表" )
    @ApiImplicitParam(name = "classId" ,dataType = "String" ,value = "传入班级classId" ,required = true )
    @GetMapping("/downloadstudent/{classId}")
    public void downloadStudent(HttpServletResponse response, @PathVariable String classId) throws IOException {
        System.out.println(classId);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            List<TSchedule> list = studentPlan(classId);
            String[] week = {"星期一","星期二","星期三","星期四","星期五"};
            List<DownloadStudent> d = new ArrayList<>();
            int i = 0;
            for (int j = 0 ; j < 5; j++) {
                DownloadStudent downloadStudent = new DownloadStudent();
                downloadStudent.setWeek(week[j]);
                String teacherName = list.get(i).getTeacherName();
                String courseName = list.get(i).getCourseName();
                i++;
                downloadStudent.setJc1(courseName+"  "+teacherName);
                teacherName = list.get(i).getTeacherName();
                courseName = list.get(i).getCourseName();
                i++;
                downloadStudent.setJc2(courseName+"  "+teacherName);
                teacherName = list.get(i).getTeacherName();
                courseName = list.get(i).getCourseName();
                i++;
                downloadStudent.setJc3(courseName+"  "+teacherName);
                teacherName = list.get(i).getTeacherName();
                courseName = list.get(i).getCourseName();
                i++;
                downloadStudent.setJc4(courseName+"  "+teacherName);
                teacherName = list.get(i).getTeacherName();
                courseName = list.get(i).getCourseName();
                i++;
                downloadStudent.setJc5(courseName+"  "+teacherName);
                teacherName = list.get(i).getTeacherName();
                courseName = list.get(i).getCourseName();
                i++;
                downloadStudent.setJc6(courseName+"  "+teacherName);
                teacherName = list.get(i).getTeacherName();
                courseName = list.get(i).getCourseName();
                i++;
                downloadStudent.setJc7(courseName+"  "+teacherName);
                d.add(downloadStudent);
            }

            d.stream().forEach( t -> System.out.println(t.toString()));

            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            //String fileName = URLEncoder.encode("测试", "UTF-8"); //火狐会乱码
            String fileName = "课程表";
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), DownloadStudent.class).autoCloseStream(Boolean.FALSE).sheet("sheet")
                    .doWrite(d);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    /**
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    @ApiOperation(value = "下载单个老师的课表" )
    @ApiImplicitParam(name = "teacherId" ,dataType = "String" ,value = "传入老师teacherId" ,required = true )
    @GetMapping("/downloadteacher/{teacherId}")
    public void downloadTeacher(HttpServletResponse response, @PathVariable String teacherId) throws IOException {
        System.out.println(teacherId);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            List<TSchedule> list = tercherPlan(teacherId);
            String[] week = {"星期一","星期二","星期三","星期四","星期五"};
            List<DownloadStudent> d = new ArrayList<>();
            int i = 0;
            for (int j = 0 ; j < 5; j++) {
                DownloadStudent downloadStudent = new DownloadStudent();
                downloadStudent.setWeek(week[j]);
                String courseName = list.get(i).getCourseName();
                String className = list.get(i).getClassName();
                i++;
                if (i > list.size() - 1)
                    break;
                downloadStudent.setJc1(courseName+"  "+className);

                className = list.get(i).getClassName();
                courseName = list.get(i).getCourseName();
                i++;
                if (i > list.size() - 1)
                    break;
                downloadStudent.setJc2(courseName+"  "+className);

                className = list.get(i).getClassName();
                courseName = list.get(i).getCourseName();
                i++;
                if (i > list.size() - 1)
                    break;
                downloadStudent.setJc3(courseName+"  "+className);

                className = list.get(i).getClassName();
                courseName = list.get(i).getCourseName();
                i++;
                if (i > list.size() - 1)
                    break;
                downloadStudent.setJc4(courseName+"  "+className);

                className = list.get(i).getClassName();
                courseName = list.get(i).getCourseName();
                i++;
                if (i > list.size() - 1)
                    break;
                downloadStudent.setJc5(courseName+"  "+className);

                className = list.get(i).getClassName();
                courseName = list.get(i).getCourseName();
                i++;
                if (i > list.size() - 1)
                    break;
                downloadStudent.setJc6(courseName+"  "+className);

                className = list.get(i).getClassName();
                courseName = list.get(i).getCourseName();
                i++;
                if (i > list.size() - 1)
                    break;
                downloadStudent.setJc7(courseName+"  "+className);

                d.add(downloadStudent);
            }

            d.stream().forEach( t -> System.out.println(t.toString()));

            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            //String fileName = URLEncoder.encode("测试", "UTF-8"); //火狐会乱码
            String fileName = "课程表";
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), DownloadStudent.class).autoCloseStream(Boolean.FALSE).sheet("sheet")
                    .doWrite(d);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }




    /**
     * 文件下载并且失败的时候返回json（默认失败了会返回一个有部分数据的Excel）
     *
     * @since 2.1.1
     */
    @ApiOperation(value = "下载所有课表/下载年级课表" )
    @GetMapping("/downloadall")
    public void downloadGrade(HttpServletResponse response,String grade) throws IOException {
        System.out.println("===debut===");
        System.out.println("grade="+grade);
        System.out.println("============");
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            List<ClassSchedule> list = findAllSchedule(grade);
            List<DownloadAll> d = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                String className = list.get(i).gettClass().getClassName();
                String classGrade = list.get(i).gettClass().getGrade();

                DownloadAll downloadAll = new DownloadAll();
                downloadAll.setClazz(classGrade+className);
                List<TSchedule> curriculum = list.get(i).getCurriculum();
                int j = 0;
                downloadAll.setJc11(curriculum.get(j++).getCourseName());
                downloadAll.setJc12(curriculum.get(j++).getCourseName());
                downloadAll.setJc13(curriculum.get(j++).getCourseName());
                downloadAll.setJc14(curriculum.get(j++).getCourseName());
                downloadAll.setJc15(curriculum.get(j++).getCourseName());
                downloadAll.setJc16(curriculum.get(j++).getCourseName());
                downloadAll.setJc17(curriculum.get(j++).getCourseName());

                downloadAll.setJc21(curriculum.get(j++).getCourseName());
                downloadAll.setJc22(curriculum.get(j++).getCourseName());
                downloadAll.setJc23(curriculum.get(j++).getCourseName());
                downloadAll.setJc24(curriculum.get(j++).getCourseName());
                downloadAll.setJc25(curriculum.get(j++).getCourseName());
                downloadAll.setJc26(curriculum.get(j++).getCourseName());
                downloadAll.setJc27(curriculum.get(j++).getCourseName());

                downloadAll.setJc31(curriculum.get(j++).getCourseName());
                downloadAll.setJc32(curriculum.get(j++).getCourseName());
                downloadAll.setJc33(curriculum.get(j++).getCourseName());
                downloadAll.setJc34(curriculum.get(j++).getCourseName());
                downloadAll.setJc35(curriculum.get(j++).getCourseName());
                downloadAll.setJc36(curriculum.get(j++).getCourseName());
                downloadAll.setJc37(curriculum.get(j++).getCourseName());

                downloadAll.setJc41(curriculum.get(j++).getCourseName());
                downloadAll.setJc42(curriculum.get(j++).getCourseName());
                downloadAll.setJc43(curriculum.get(j++).getCourseName());
                downloadAll.setJc44(curriculum.get(j++).getCourseName());
                downloadAll.setJc45(curriculum.get(j++).getCourseName());
                downloadAll.setJc46(curriculum.get(j++).getCourseName());
                downloadAll.setJc47(curriculum.get(j++).getCourseName());

                downloadAll.setJc51(curriculum.get(j++).getCourseName());
                downloadAll.setJc52(curriculum.get(j++).getCourseName());
                downloadAll.setJc53(curriculum.get(j++).getCourseName());
                downloadAll.setJc54(curriculum.get(j++).getCourseName());
                downloadAll.setJc55(curriculum.get(j++).getCourseName());
                downloadAll.setJc56(curriculum.get(j++).getCourseName());
                downloadAll.setJc57(curriculum.get(j++).getCourseName());

                d.add(downloadAll);
            }

            d.stream().forEach( t -> System.out.println(t.toString()));

            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            //String fileName = URLEncoder.encode("测试", "UTF-8"); //火狐会乱码
            String fileName = "课程表";
            if(grade!=null){
                //如果年级不为null，修改文件名为“初一课程表，初二课程表...”
                fileName = grade + fileName;
                System.out.println("====fileName="+fileName);
            }

            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), DownloadAll.class).autoCloseStream(Boolean.FALSE).sheet("sheet")
                    .doWrite(d);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

}
