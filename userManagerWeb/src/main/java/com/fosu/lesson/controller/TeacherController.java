package com.fosu.lesson.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TTeacher;
import com.fosu.lesson.service.TeacherService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/teacher")
@Api(tags = "基本信息管理-老师")
public class TeacherController {

    @Reference
    private TeacherService teacherService;

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查找" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo" , dataType = "int" ,value = "页码" ) ,
            @ApiImplicitParam(name = "pageSize" , dataType = "int" , value = "一页的条数")
    })
    public PageResult findByPage(int pageNo, int pageSize) {
        System.out.println(pageNo+"======="+pageSize);
        return teacherService.findByPage(pageNo, pageSize);
    }

    @PostMapping("/findOne")
    @ApiOperation(value = "查找一个老师信息" , notes = "通过老师的部分信息获取老师的完整信息")
    @ApiImplicitParam(name = "tTeacher" ,dataType = "TTeacher" , value = "老师的部分信息作为参数" ,required = true )
    public TTeacher findOne(@RequestBody TTeacher tTeacher){
        return teacherService.findOne(tTeacher);
    }

    @ApiOperation(value = "查找所有老师信息")
    @GetMapping("/findAll")
    public List findAll(){
        return teacherService.findAll();
    }

    //增加
    @PostMapping("/save")
    @ApiOperation(value = "新增老师信息" )
    @ApiImplicitParam(name = "tTeacher" ,dataType = "TTeacher" , value = "新增的老师信息" ,required = true )
    public boolean save(@RequestBody TTeacher tTeacher){
        int flag = 1;
        try {
            teacherService.save(tTeacher);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "删除老师信息" )
    @ApiImplicitParam(name = "ids" ,allowMultiple = true, dataType = "String" ,value = "删除老师信息" ,required = true )
    @GetMapping("/deleteByIds")
    public boolean del(String[] ids){
        System.out.println("===============controller=============");
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        System.out.println("===============controller===========end==");
        int flag = 1;
        try {
            teacherService.delete(ids);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        System.out.println("flag====="+flag);
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "更新老师信息" )
    @ApiImplicitParam(name = "tTeacher" , dataType = "TTeacher" , value = "更新的老师信息" ,required = true )
    @PostMapping("/update")
    public boolean update(@RequestBody TTeacher tTeacher){
        int flag = 1;
        try {
            teacherService.update(tTeacher);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }
}