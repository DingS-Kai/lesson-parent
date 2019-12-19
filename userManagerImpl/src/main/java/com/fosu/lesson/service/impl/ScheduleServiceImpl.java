package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TScheduleMapper;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.pojo.TScheduleExample;
import com.fosu.lesson.pojo.TStudent;
import com.fosu.lesson.service.ScheduleService;
import com.fosu.lesson.utils.ScheduleUtils;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.function.Consumer;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private TScheduleMapper tScheduleMapper;

    @Autowired
    private   ScheduleUtils scheduleUtils;


    @Override
    public List<TSchedule> findOne(TSchedule tSchedule) {
        TScheduleExample tScheduleExample = new TScheduleExample();

        tScheduleExample.createCriteria().andClassIdEqualTo(tSchedule.getClassId());

        List<TSchedule> list= tScheduleMapper.selectByExample(tScheduleExample);

        System.out.println("===================================================");
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);
        System.out.println("===================================================");

        return list;
    }

    @Override
    public Map<String, List<TSchedule>> findAll() {
        //查找有多少个班级


        List<TSchedule> list = tScheduleMapper.selectByExample(null);
        Map<String, List<TSchedule>> map = new HashMap<>();

        return null;
    }

    @Override
    public void save(TSchedule tSchedule) {
        tScheduleMapper.insertSelective(tSchedule);
    }

    @Override
    public void update(TSchedule tSchedule) {
        TScheduleExample tScheduleExample = new TScheduleExample();
        tScheduleExample.createCriteria().andIdEqualTo(tSchedule.getId());
        tScheduleMapper.updateByExampleSelective(tSchedule, tScheduleExample);
    }

    @Override
    public void delete(Integer[] ids) {
        TScheduleExample tScheduleExample = new TScheduleExample();
        tScheduleExample.createCriteria().andIdIn(Arrays.asList(ids));
    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize) {
        PageResult pageResult = new PageResult();
        Page<TStudent> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(
                new ISelect() {
                    @Override
                    public void doSelect() {
                        tScheduleMapper.selectByExample(null);
                    }
                }
        );

        List<TStudent> result = page.getResult();
        Consumer consumer = (item) -> System.out.println(item);
        result.forEach(consumer);

        pageResult.setRows(page.getResult());
        pageResult.setTotal(page.getTotal());
        return pageResult;
    }

    @Override
    public void shcedule() {

        //排课
        scheduleUtils.schedulePlan();
        //更像相关属性
        tScheduleMapper.updateName();
    }

    @Override
    public List<TSchedule> getOneTercherPlan(String tercherId) {

        TScheduleExample tScheduleExample = new TScheduleExample();

        tScheduleExample.createCriteria().andTeacherIdEqualTo(tercherId);

        List<TSchedule> list= tScheduleMapper.selectByExample(tScheduleExample);

        //根据时间片排序
        Collections.sort(list, new Comparator<TSchedule>() {
            @Override
            public int compare(TSchedule o1, TSchedule o2) {
                if(o1.getTimeId().length()>o2.getTimeId().length()){
                    return 1;
                }else if(o1.getTimeId().length()==o2.getTimeId().length()){
                    return o1.getTimeId().compareTo(o2.getTimeId());
                }
                return -1;
            }
        });

        System.out.println("===================================================");
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);
        System.out.println("===================================================");
        return list;
    }

    @Override
    public List<TSchedule> getOneStudentPlan(String classID) {
        TScheduleExample tScheduleExample = new TScheduleExample();

        tScheduleExample.createCriteria().andClassIdEqualTo(classID);

        List<TSchedule> list= tScheduleMapper.selectByExample(tScheduleExample);

        Collections.sort(list, new Comparator<TSchedule>() {
            @Override
            public int compare(TSchedule o1, TSchedule o2) {
                if(o1.getTimeId().length()>o2.getTimeId().length()){
                    return 1;
                }else if(o1.getTimeId().length()==o2.getTimeId().length()){
                    return o1.getTimeId().compareTo(o2.getTimeId());
                }
                return -1;
            }
        });

        System.out.println("===================================================");
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);
        System.out.println("===================================================");

        return list;
    }

}
