package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TClassMapper;
import com.fosu.lesson.pojo.PageResult;
import com.fosu.lesson.pojo.TClass;
import com.fosu.lesson.pojo.TClassExample;
import com.fosu.lesson.pojo.TStudent;
import com.fosu.lesson.service.ClassService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private TClassMapper tClassMapper;

    @Override
    public TClass findOne(TClass tClass) {
        return tClassMapper.selectOne(tClass);
    }

    @Override
    public List<TClass> findAll() {
        return tClassMapper.selectByExample(null);
    }

    @Override
    public void save(TClass tClass) {
        tClassMapper.insertSelective(tClass);
    }

    @Override
    public void update(TClass tClass) {
        TClassExample tClassExample = new TClassExample();
        TClassExample.Criteria criteria = tClassExample.createCriteria();
        criteria.andClassIdEqualTo(tClass.getClassId());
        tClassMapper.updateByExampleSelective(tClass, tClassExample);
    }

    @Override
    public void delete(String[] ids) {
        TClassExample tClassExample = new TClassExample();
        TClassExample.Criteria criteria = tClassExample.createCriteria();

        criteria.andClassIdIn(Arrays.asList(ids));
        tClassMapper.deleteByExample(tClassExample);
    }

    @Override
    public PageResult findByPage(int pageNo, int pageSize) {
        PageResult pageResult = new PageResult();
        Page<TStudent> page = PageHelper.startPage(pageNo, pageSize).doSelectPage(
                new ISelect() {
                    @Override
                    public void doSelect() {
                        tClassMapper.selectByExample(null);
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
    public List<TClass> findClass(String grade) {
        TClassExample tClassExample = new TClassExample();
        TClassExample.Criteria criteria = tClassExample.createCriteria();

        criteria.andGradeEqualTo(grade);
        List<TClass> list = tClassMapper.selectByExample(tClassExample);

        System.out.println("===========================");
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);
        System.out.println("========================================");

      /*  List<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String s = (list.get(i)).getClassName();
            list1.add(s);
        }*/
        return list;
    }

    @Override
    public List<String> findGrade() {
        List<String> list = tClassMapper.findGrade();

        System.out.println("===========================");
        Consumer consumer = (item) -> System.out.println(item.toString());
        list.forEach(consumer);
        System.out.println("========================================");

        return list;
    }
}