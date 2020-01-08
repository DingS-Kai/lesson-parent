package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TClassMapper;
import com.fosu.lesson.dao.TPrescheduleMapper;
import com.fosu.lesson.dao.TScheduleMapper;
import com.fosu.lesson.dao.TScheduletaskMapper;
import com.fosu.lesson.pojo.*;
import com.fosu.lesson.service.ScheduleService;
import com.fosu.lesson.utils.ScheduleUtils;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private TPrescheduleMapper prescheduleMapper;

    @Autowired
    private TScheduleMapper tScheduleMapper;

    @Autowired
    private   ScheduleUtils scheduleUtils;

    @Autowired
    private TClassMapper tClassMapper;

    @Autowired
    private TScheduletaskMapper tScheduletaskMapper;


    @Override
    public List<TSchedule> findOne(TSchedule tSchedule,Boolean flag) {
        TScheduleExample tScheduleExample = new TScheduleExample();
        if(flag==true){
            tScheduleExample.createCriteria().andClassIdEqualTo(tSchedule.getClassId());
        }else{
            tScheduleExample.createCriteria().andTeacherNameEqualTo(tSchedule.getTeacherName());
        }


        List<TSchedule> list= tScheduleMapper.selectByExample(tScheduleExample);

        System.out.println("===================================================");
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);
        System.out.println("===================================================");

        return list;
    }

    @Override
    public Map<String, List<TSchedule>> findAll(String grade) {
            //查找有多少个班级
            List<String> distinctClassId = tScheduleMapper.findDistinctClassId(grade);
        System.out.println("==============================="+grade);
            Map<String, List<TSchedule>> map = new HashMap<>();

            for (int i = 0; i < distinctClassId.size(); i++) {
                String classId = distinctClassId.get(i);
                List<TSchedule> oneStudentPlan = getOneStudentPlan(classId);
                map.put(classId, oneStudentPlan);
            }

            System.out.println("================================");
            BiConsumer biConsumer = (k, v) -> {
                System.out.println(k.toString());
                System.out.println(v.toString());
                System.out.println();
            };
            map.forEach(biConsumer);
            System.out.println("================================");
            return map;
    }

    @Override
    public List<ClassSchedule> findAllSchedule(String grade){
        //查找有多少个班级
        //List<String> distinctClassId = tScheduleMapper.findDistinctClassId();
        //查找所有的班级
        List<TClass> classList;
        if(grade==null||grade.equals("")){
            classList = tClassMapper.selectByExample(null);
        }else{
            TClassExample tClassExample = new TClassExample();
            tClassExample.createCriteria().andGradeEqualTo(grade);
            classList = tClassMapper.selectByExample(tClassExample);
        }


        List<ClassSchedule> list = new ArrayList<>();

        for (int i = 0; i < classList.size(); i++) {
            TClass tClass = classList.get(i); //获得每一个班级
            String classId = tClass.getClassId();  //获得每个班级的id
            List<TSchedule> oneStudentPlan = getOneStudentPlan(classId);
            ClassSchedule cs = new ClassSchedule();

            //班级的课表存在，则把班级信息和课表信息添加到封装类classSchedule中
            if( oneStudentPlan!=null && oneStudentPlan.size()>0 ) {
                cs.settClass(tClass);
                cs.setCurriculum(oneStudentPlan);
            }else{
                continue;
            }
            list.add(cs);
        }

        System.out.println("================================");
        for (ClassSchedule cs: list ) {
            System.out.println("classId:"+cs.gettClass().getClassId());
            System.out.println(cs.getCurriculum());
            System.out.println("===");
        }
        System.out.println("================================");
        return list;
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

    @Transactional
    @Override
    public void shcedule(String grade) {


        //清空的数据
        tScheduleMapper.deleteAll();
        //排课
        scheduleUtils.schedulePlan(null,null);
        //更像相关属性
        tScheduleMapper.updateName();
    }

    @Override
    public void shcedule(List<TSchedule> tScheduleList,String grade) {
        if(grade==null||grade.equals("")){
            //清空的数据
            tScheduleMapper.deleteAll();
        }else{

            tScheduleMapper.deleteOneGrade(grade);
        }
        List<TPreschedule> tPreschedules = prescheduleMapper.selectByExample(null);
        //排课
        scheduleUtils.schedulePlan(tPreschedules,grade);
        //更像相关属性
        tScheduleMapper.updateName();
        //更改状态
        tScheduletaskMapper.updateStatu(grade);
        //清空预排表
       // prescheduleMapper.deleteAll();

    }

    @Override
    public void clearSchedule(String grade) {
        if(grade==null||grade.equals("")){
            //清空的数据
            tScheduleMapper.deleteAll();
        }else{

            tScheduleMapper.deleteOneGrade(grade);
        }
        tScheduletaskMapper.cleanStatu(grade);
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
        for(int i=0;i<list.size();i++){
            if(list.get(i).getTimeId().equals((i+1)+"")){
                continue;
            }else{
                TSchedule tSchedule=new TSchedule();
                tSchedule.setTimeId((i+1)+"");
                list.add(i,tSchedule);
            }
        }

        System.out.println("===================================================");
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);
        System.out.println("===================================================");

        return list;
    }

    @Override
    public List<TSchedule> getOneStudentPlan(String classID) {
        TScheduleExample tScheduleExample = new TScheduleExample();
        System.out.println("classId==== "+classID);
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

        for(int i=0;i<list.size();i++){
            if(list.get(i).getTimeId().equals((i+1)+"")){
                continue;
            }else{
                TSchedule tSchedule=new TSchedule();
                tSchedule.setTimeId((i+1)+"");
                list.add(i,tSchedule);
                i=36;
            }
        }
        System.out.println("===================================================");
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);
        System.out.println("===================================================");
        return list;
    }

    @Override
    public List<String> findDistinctClassId() {
        return null;
    }



}
