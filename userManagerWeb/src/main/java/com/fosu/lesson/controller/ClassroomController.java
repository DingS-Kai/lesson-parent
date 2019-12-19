package com.fosu.lesson.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TClassroom;
import com.fosu.lesson.service.ClassroomService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/classroom")
@Api(tags = "基本信息管理-教室")
public class ClassroomController {

    @Reference
    private ClassroomService classroomService;

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查找" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo" , value = "页码" , dataType = "int") ,
            @ApiImplicitParam(name = "pageSize" , value = "一页的条数" , dataType = "int")
    })
    public PageResult findByPage(int pageNo, int pageSize) {
        System.out.println(pageNo+"======="+pageSize);
        return classroomService.findByPage(pageNo, pageSize);
    }

    @PostMapping("/findOne")
    @ApiOperation(value = "查找一个教室信息" , notes = "通过教室的部分信息获取教室的完整信息")
    @ApiImplicitParam(name = "tClassroom" , dataType = "TClassroom" ,value = "教室的部分信息作为参数" ,required = true )
    public TClassroom findOne(@RequestBody TClassroom tClassroom){
        System.out.println(tClassroom.getClassroomId()+"-----------"+tClassroom.getClassroomNum());
        tClassroom = classroomService.findOne(tClassroom);
        System.out.println(tClassroom.getPlace()+"==="+tClassroom.getClassroomNum());
        return tClassroom;
        //return classroomService.findOne(tClassroom);
    }

    @ApiOperation(value = "查找所有教室信息")
    @GetMapping("/findAll")
    public List findAll(){
        return classroomService.findAll();
    }

    //增加
    @PostMapping("/save")
    @ApiOperation(value = "新增教室信息" )
    @ApiImplicitParam(name = "tClassroom" , dataType = "TClassroom" , value = "新增的教室信息,classroomId不能为空" ,required = true )
    public boolean save(@RequestBody TClassroom tClassroom){
        System.out.println("===============controller=============");
        System.out.println(tClassroom.toString());
        System.out.println("===============controller===========end==");
        int flag = 1;
        try {
            classroomService.save(tClassroom);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }


    //allowMultiple=true,————表示是数组格式的参数
    //dataType = "String"————表示数组中参数的类型
    @ApiOperation(value = "删除教室信息" )
    @ApiImplicitParam(name = "ids" ,allowMultiple = true, dataType = "String", value = "删除教室信息" ,required = true )
    @GetMapping("/deleteByIds")
    public boolean del(String[] ids){
        System.out.println("===============controller=============");
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        System.out.println("===============controller===========end==");
        int flag = 1;
        try {
            classroomService.delete(ids);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        System.out.println("flag====="+flag);
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "更新教室信息" )
    @ApiImplicitParam(name = "tClassroom" , dataType = "TClassroom" ,value = "更新的教室信息" ,required = true )
    @PostMapping("/update")
    public boolean update(@RequestBody TClassroom tClassroom){
        System.out.println("===============controller=============");
        System.out.println(tClassroom.toString());
        System.out.println("===============controller===========end==");
        int flag = 1;
        try {
            classroomService.update(tClassroom);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }
}