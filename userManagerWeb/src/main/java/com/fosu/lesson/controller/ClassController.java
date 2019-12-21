package com.fosu.lesson.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TClass;
import com.fosu.lesson.service.ClassService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/class")
@Api(tags = "基本信息管理-班别")
public class ClassController {
    @Reference
    private ClassService classService;

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查找" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo" , dataType = "int" ,value = "页码" ) ,
            @ApiImplicitParam(name = "pageSize" , dataType = "int" , value = "一页的条数")
    })
    public PageResult findByPage(int pageNo, int pageSize) {
        System.out.println(pageNo+"======="+pageSize);
        return classService.findByPage(pageNo, pageSize);
    }

    @PostMapping("/findOne")
    @ApiOperation(value = "查找一个班别信息" , notes = "通过班别的部分信息获取班别的完整信息")
    @ApiImplicitParam(name = "tClass" ,dataType = "TClass" , value = "班别的部分信息作为参数" ,required = true )
    public TClass findOne(@RequestBody TClass tClass){
        return classService.findOne(tClass);
    }

    @ApiOperation(value = "查找所有班别信息")
    @GetMapping("/findAll")
    public List findAll(){
        return classService.findAll();
    }

    //增加
    @PostMapping("/save")
    @ApiOperation(value = "新增班别信息" )
    @ApiImplicitParam(name = "tClass" ,dataType = "TClass" , value = "新增的班别信息" ,required = true )
    public boolean save(@RequestBody TClass tClass){
        int flag = 1;
        try {
            classService.save(tClass);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "删除班别信息" )
    @ApiImplicitParam(name = "ids" ,allowMultiple = true, dataType = "String" ,value = "删除班别信息" ,required = true )
    @GetMapping("/deleteByIds")
    public boolean del(String[] ids){
        System.out.println("===============controller=============");
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        System.out.println("===============controller===========end==");
        int flag = 1;
        try {
            classService.delete(ids);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        System.out.println("flag====="+flag);
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "更新班别信息" )
    @ApiImplicitParam(name = "tClass" , dataType = "TClass" , value = "更新的班别信息" ,required = true )
    @PostMapping("/update")
    public boolean update(@RequestBody TClass tClass){
        int flag = 1;
        try {
            classService.update(tClass);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }


    @ApiOperation(value = "查找一个年级有几个班,返回该年级对应的班别" )
    @ApiImplicitParam(name = "grade" , dataType = "String" , value = "传入一个年级grade" ,required = true )
    @GetMapping("/findclass/{grade}")
    public List<String> findClass(@PathVariable String grade){
        System.out.println(grade);
        return classService.findClass(grade);
    }

    @ApiOperation(value = "返回所有的年级" )
    @GetMapping("/findgrade")
    public List<String> findGrade(){
        return classService.findGrade();
    }

}