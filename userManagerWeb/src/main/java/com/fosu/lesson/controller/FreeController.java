package com.fosu.lesson.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TFree;
import com.fosu.lesson.service.FreeService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/free")
@Api(tags = "基本信息管理-教室空闲表")
public class FreeController {
    
    @Reference
    private FreeService freeService;

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页查找" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo" , dataType = "int" ,value = "页码" ) ,
            @ApiImplicitParam(name = "pageSize" , dataType = "int" , value = "一页的条数")
    })
    public PageResult findByPage(int pageNo, int pageSize) {
        System.out.println(pageNo+"======="+pageSize);
        return freeService.findByPage(pageNo, pageSize);
    }

    @PostMapping("/findOne")
    @ApiOperation(value = "查找一个教室空闲表信息" , notes = "通过教室空闲表的部分信息获取教室空闲表的完整信息")
    @ApiImplicitParam(name = "tFree" ,dataType = "TFree" , value = "教室空闲表的部分信息作为参数" ,required = true )
    public TFree findOne(@RequestBody TFree tFree){
        System.out.println(" findOne(@RequestBody TFree tFree)=="+tFree.toString());
        return freeService.findOne(tFree);
    }

    @ApiOperation(value = "查找所有教室空闲表信息")
    @GetMapping("/findAll")
    public List findAll(){
        return freeService.findAll();
    }

    //增加
    @PostMapping("/save")
    @ApiOperation(value = "新增教室空闲表信息" )
    @ApiImplicitParam(name = "tFree" ,dataType = "TFree" , value = "新增的教室空闲表信息" ,required = true )
    public boolean save(@RequestBody TFree tFree){
        System.out.println(" save(@RequestBody TFree tFree)=="+tFree.toString());
        int flag = 1;
        try {
            freeService.save(tFree);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "删除教室空闲表信息" )
    @ApiImplicitParam(name = "list" ,allowMultiple = true, dataType = "TFree" ,value = "删除教室空闲表信息" ,required = true )
    @GetMapping("/deleteByIds")
    public boolean del(List<TFree> list){
        System.out.println("===============controller=============");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        System.out.println("===============controller===========end==");
        int flag = 1;
        try {
            freeService.delete(list);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        System.out.println("flag====="+flag);
        return flag == 1 ? true : false;
    }

    @ApiOperation(value = "更新教室空闲表信息" )
    @ApiImplicitParam(name = "tFree" , dataType = "TFree" , value = "更新的教室空闲表信息" ,required = true )
    @PostMapping("/update")
    public boolean update(@RequestBody TFree tFree){
        System.out.println(" update(@RequestBody TFree tFree)=="+tFree.toString());
        int flag = 1;
        try {
            freeService.update(tFree);
        } catch (Exception e) {
            flag = 0;
            e.printStackTrace();
        }
        return flag == 1 ? true : false;
    }
}