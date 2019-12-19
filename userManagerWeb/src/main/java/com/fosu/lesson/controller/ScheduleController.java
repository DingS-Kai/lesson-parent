package com.fosu.lesson.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.service.CourseService;
import com.fosu.lesson.service.ScheduleService;
import com.fosu.lesson.service.TeacherService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/schedule")
@Api(tags = "基本信息管理-排课")
public class ScheduleController {

    @Reference
    private ScheduleService scheduleService;
    @Reference
    private CourseService courseService;
    @Reference
    private TeacherService teacherService;

    @GetMapping("hhhh")
    public List<TSchedule> qryOne(){
        TSchedule tSchedule1 = new TSchedule();
        TSchedule tSchedule2 = new TSchedule();
        List<String> classNoList = courseService.selectByColumnName("class_id");
        List<String> teacherIdList = teacherService.selectByColumnName("teacher_name");
        /*for(String class_name:classNoList){
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
*/
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
    //排课
    @GetMapping("mymy")
    public void schedulePlan(){

         scheduleService.shcedule();
    }

    //单个老师的课表
    @GetMapping("TeacherPlan")
    public void tercherPlan(){
        String tercherId="20160312";
        List<TSchedule> tScheduleList =scheduleService.getOneTercherPlan(tercherId);
    }

    //单个学生的课表
    @GetMapping("StudentPlan")
    public void StudentPlan(){
        String classID="701";
        List<TSchedule> tScheduleList =scheduleService.getOneStudentPlan(classID);
    }

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查找" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo" , dataType = "int" ,value = "页码" ) ,
            @ApiImplicitParam(name = "pageSize" , dataType = "int" , value = "一页的条数")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好") ,
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public PageResult findByPage(int pageNo, int pageSize) {
        System.out.println(pageNo+"======="+pageSize);
        return scheduleService.findByPage(pageNo, pageSize);
    }

    @PostMapping("/findOne")
    @ApiOperation(value = "查找一个班级排课信息" , notes = "通过班级排课的部分信息获取班级排课的完整信息")
    @ApiImplicitParam(name = "tSchedule" ,dataType = "TSchedule" , value = "班级排课的部分信息作为参数" ,required = true )
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public List<TSchedule> findOne(@RequestBody TSchedule tSchedule){
        return scheduleService.findOne(tSchedule,true);
    }

    @ApiOperation(value = "查找所有班级排课信息")
    @GetMapping("/findAll")
    public List findAll(){
        return null;
        //return scheduleService.findAll();
    }


    //增加
    @PostMapping("/save")
    @ApiOperation(value = "新增班级排课信息" )
    @ApiImplicitParam(name = "tSchedule" ,dataType = "TSchedule" , value = "新增的班级排课信息" ,required = true )
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
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
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
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
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
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
}
