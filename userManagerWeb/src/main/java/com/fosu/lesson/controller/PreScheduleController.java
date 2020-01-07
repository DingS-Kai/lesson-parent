package com.fosu.lesson.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TPreschedule;
import com.fosu.lesson.service.PreScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/preschedule")
@Api(tags = "预排表的增删改查")
public class PreScheduleController {
    @Reference
    private PreScheduleService preScheduleService;

    @GetMapping("/findOne")
    @ApiOperation("/查询一节预排的课程信息")
    @ApiImplicitParam(name="id",value="传入的id")
    public TPreschedule findOne(@RequestParam  Integer id) {
        return this.preScheduleService.findOne(id);
    }

    @GetMapping("/findAll")
    @ApiOperation("查询某个班级的所有预排的课程信息")
    @ApiImplicitParam(name="classId",value="传入的classId")
    public List<TPreschedule> findAll(String classId) {
        return this.preScheduleService.findAll(classId);
    }

    @PostMapping("/save")
    @ApiOperation("添加一节预排课程")
    @ApiImplicitParam(name="tPreschedule",value="添加的预排课程对象",dataType="TPreschedule",paramType="body",required=true )
    public TPreschedule save(@RequestBody  TPreschedule tPreschedule) {
        System.out.println("{DEBUG}:添加一节预排课程  "+tPreschedule);
        return this.preScheduleService.save(tPreschedule);
    }

    @PostMapping("/update")
    @ApiOperation("修改一节预排课程")
    @ApiImplicitParam(name="tPreschedule",value="新的预排课程对象",dataType="TPreschedule",paramType="body",required=true )
    public void update(@RequestBody  TPreschedule tPreschedule) {
        System.out.println("{DEBUG}:修改一节预排课程  "+tPreschedule);
        this.preScheduleService.update(tPreschedule);
    }

    @GetMapping("/delete")
    @ApiOperation("批量删除某个班级的预排课程")
    @ApiImplicitParam(name="ids",value="存放多个id的数组",dataType="Integer",paramType="query",required=true,allowMultiple=true)
    public void delete(Integer[] ids) {
        this.preScheduleService.delete(ids);
    }

    @GetMapping("/findByPage")
    @ApiOperation("分页查询某个班级的预排信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNo",value="当前页码",defaultValue="1",dataType="int",required=false),
            @ApiImplicitParam(name="pageSize",value="每页大小",defaultValue="5",dataType="int",required=false),
            @ApiImplicitParam(name="classId",value="传入的classId",dataType="String",required=false)
    })
    public PageResult findByPage(int pageNo, int pageSize,String classId) {
        return this.preScheduleService.findByPage(pageNo,pageSize,classId);
    }
}
