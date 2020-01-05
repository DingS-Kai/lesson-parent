package com.fosu.lesson.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.CourseWithTeacher;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TTeacher;
import com.fosu.lesson.service.CourseService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/course")
@Api(tags = "基本信息管理-课程表")
public class CourseControlle {

    @Reference
    private CourseService courseService;



    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查找" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo" , dataType = "int" ,value = "页码" ) ,
            @ApiImplicitParam(name = "pageSize" , dataType = "int" , value = "一页的条数")
    })
    public PageResult findByPage(int pageNo, int pageSize) {
        System.out.println(pageNo+"======="+pageSize);
        return courseService.findByPage(pageNo, pageSize);
    }

    @PostMapping("/findOne")
    @ApiOperation(value = "查找一个课程表信息" , notes = "通过课程表的部分信息获取课程表的完整信息")
    @ApiImplicitParam(name = "tCourse" ,dataType = "TCourse" , value = "课程表的部分信息作为参数" ,required = true )
    public TCourse findOne(@RequestBody TCourse tCourse){
        return courseService.findOne(tCourse);
    }

    @ApiOperation(value = "查找所有课程表信息")
    @GetMapping("/findAll")
    public List findAll(){
        return courseService.findAll();
    }



    //增加
    @PostMapping("/save")
    @ApiOperation(value = "新增课程表信息" )
    @ApiImplicitParam(name = "tCourse" ,dataType = "TCourse" , value = "新增的课程表信息" ,required = true )
    public boolean save(@RequestBody TCourse tCourse){
        int flag = 1;
        try {
            courseService.save(tCourse);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "删除课程表信息" )
    @ApiImplicitParam(name = "ids" ,allowMultiple = true, dataType = "String" ,value = "删除课程表信息" ,required = true )
    @GetMapping("/deleteByIds")
    public boolean del(String[] ids){
        System.out.println("===============controller=============");
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        System.out.println("===============controller===========end==");
        int flag = 1;
        try {
            courseService.delete(ids);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        System.out.println("flag====="+flag);
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "更新课程表信息" )
    @ApiImplicitParam(name = "tCourse" , dataType = "TCourse" , value = "更新的课程表信息" ,required = true )
    @PostMapping("/update")
    public boolean update(@RequestBody TCourse tCourse){
        int flag = 1;
        try {
            courseService.update(tCourse);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }

    @PostMapping("/findCourseWithTeacherByCondition")
    @ApiOperation("根据条件查找某班级的课程及课程对应的老师信息")
    @ApiImplicitParam(name="condition",value = "传入的条件", dataType = "CourseWithTeacher",
            paramType = "body", required = true)
    public List<CourseWithTeacher> findCourseWithTeacherByCondition(@RequestBody  CourseWithTeacher condition){
        System.out.println("{DEBUG}:"+condition);
        return this.courseService.findCourseWithTeacherByCondition(condition);
    }

    @GetMapping("/findTeacherByCourseName")
    @ApiOperation("通过课程查询教授该课程的老师（不需要用到）")
    @ApiImplicitParam(name="courseName",value = "传入的课程名", dataType = "String", paramType = "query", required = true)
    public List<CourseWithTeacher> findTeacherByCourseName(@RequestParam String courseName) {
        return this.courseService.findTeacherByCourseName(courseName);
    }

}