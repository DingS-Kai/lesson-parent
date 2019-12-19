package com.fosu.lesson.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TStudent;
import com.fosu.lesson.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
@Api(tags = "基本信息管理-学生")
public class StudentController {

    @Reference
    private StudentService studentService;

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查找" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo" , dataType = "int" ,value = "页码" ) ,
            @ApiImplicitParam(name = "pageSize" , dataType = "int" , value = "一页的条数")
    })
    public PageResult findByPage(int pageNo, int pageSize) {
        System.out.println(pageNo+"======="+pageSize);
        return studentService.findByPage(pageNo, pageSize);
    }

    @PostMapping("/findOne")
    @ApiOperation(value = "查找一个学生信息" , notes = "通过学生的部分信息获取学生的完整信息")
    @ApiImplicitParam(name = "tStudent" ,dataType = "TStudent" , value = "学生的部分信息作为参数" ,required = true )
    public TStudent findOne(@RequestBody TStudent tStudent){
        return studentService.findOne(tStudent);
    }

    @ApiOperation(value = "查找所有学生信息")
    @GetMapping("/findAll")
    public List findAll(){
        return studentService.findAll();
    }

    //增加
    @PostMapping("/save")
    @ApiOperation(value = "新增学生信息" )
    @ApiImplicitParam(name = "tStudent" ,dataType = "TStudent" , value = "新增的学生信息" ,required = true )
    public boolean save(@RequestBody TStudent tStudent){
        int flag = 1;
        try {
            studentService.save(tStudent);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "删除学生信息" )
    @ApiImplicitParam(name = "ids" ,allowMultiple = true, dataType = "String" ,value = "删除学生信息" ,required = true )
    @GetMapping("/deleteByIds")
    public boolean del(String[] ids){
        System.out.println("===============controller=============");
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        System.out.println("===============controller===========end==");
        int flag = 1;
        try {
            studentService.delete(ids);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        System.out.println("flag====="+flag);
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "更新学生信息" )
    @ApiImplicitParam(name = "tStudent" , dataType = "TStudent" , value = "更新的学生信息" ,required = true )
    @PostMapping("/update")
    public boolean update(@RequestBody TStudent tStudent){
        int flag = 1;
        try {
            studentService.update(tStudent);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }
}