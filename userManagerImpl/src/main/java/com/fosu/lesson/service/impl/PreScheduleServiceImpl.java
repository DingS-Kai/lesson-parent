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
    public void save(TPreschedule tPreschedule) {
        this.prescheduleMapper.insertSelective(tPreschedule);
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
                        example.createCriteria().andClassIdEqualTo(classId);
                        //SELECT * FROM t_preschedule WHERE class_id=805 ORDER BY time_id*1;
                        //这里把数字字符串按数字排序
                        example.setOrderByClause("time_id * 1");
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
