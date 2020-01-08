package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TPrescheduleMapper;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TPreschedule;
import com.fosu.lesson.pojo.TPrescheduleExample;
import com.fosu.lesson.service.PreScheduleService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public class PreScheduleServiceImpl implements PreScheduleService {

    @Autowired
    private TPrescheduleMapper prescheduleMapper;


    @Override
    public TPreschedule findOne(Integer id) {
        TPrescheduleExample example = new TPrescheduleExample();
        example.createCriteria().andIdEqualTo(id);
        TPreschedule tPreschedule = this.prescheduleMapper.selectByExample(example).get(0);
        return tPreschedule;
    }

    @Override
    public List<TPreschedule> findAll(String classId) {
        TPrescheduleExample example = new TPrescheduleExample();
        example.createCriteria().andClassIdEqualTo(classId);
        return this.prescheduleMapper.selectByExample(example);
    }

    @Override
    public TPreschedule save(TPreschedule tPreschedule) {
        TPrescheduleExample example = new TPrescheduleExample();
        //添加之前，先进行判断
        //先判断该老师是否在该时间段有课
        example.createCriteria().andTimeIdEqualTo(tPreschedule.getTimeId())
                                .andTeacherIdEqualTo(tPreschedule.getTeacherId());
        List<TPreschedule> hasList = this.prescheduleMapper.selectByExample(example);
        if(hasList!=null && hasList.size()>0){
            //说明该老师已经有课
            return hasList.get(0);
        }


        //如果改班级在该节课已经有预排，则直接修改
        //根据classId 和 timeId查询
        example = new TPrescheduleExample();//重新new一个example
        example.createCriteria().andClassIdEqualTo(tPreschedule.getClassId())
                                .andTimeIdEqualTo(tPreschedule.getTimeId());
        List<TPreschedule> list = this.prescheduleMapper.selectByExample(example);
        if(list!=null && list.size()>0){
            //说明已经为该班的该节课添加过预排
            System.out.println("添加预排重复："+list.get(0));
            TPreschedule oldPreschedule = list.get(0);
            tPreschedule.setId(oldPreschedule.getId());
            this.update(tPreschedule);
        }else{
            this.prescheduleMapper.insertSelective(tPreschedule);
        }
        //成功添加和修改，返回null
        return null;

    }

    @Override
    public void update(TPreschedule tPreschedule) {
        TPrescheduleExample example = new TPrescheduleExample();
        example.createCriteria().andIdEqualTo(tPreschedule.getId());
        this.prescheduleMapper.updateByExample(tPreschedule,example);
    }

    @Override
    public void delete(Integer[] ids) {
        TPrescheduleExample example = new TPrescheduleExample();
        TPrescheduleExample.Criteria criteria =  example.createCriteria();

        criteria.andIdIn(Arrays.asList(ids));
        this.prescheduleMapper.deleteByExample(example);

    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize, String classId) {
        PageResult pageResult = new PageResult();
        Page<TPreschedule> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(
                new ISelect() {
                    @Override
                    public void doSelect() {
                        TPrescheduleExample example = new TPrescheduleExample();
                        if(classId!=null){
                            example.createCriteria().andClassIdEqualTo(classId);
                        }
                        //SELECT * FROM t_preschedule WHERE class_id=805 ORDER BY time_id*1;
                        //这里把数字字符串按数字排序
                        example.setOrderByClause("class_id, time_id * 1");
                        prescheduleMapper.selectByExample(example);
                    }
                }
        );

        List<TPreschedule> result = page.getResult();
        Consumer consumer = (item) -> System.out.println(item);
        result.forEach(consumer);

        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }
}
