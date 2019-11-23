package com.fosu.lesson.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TScheduleMapper;
import com.fosu.lesson.dao.UserManagerMapper;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.pojo.TScheduleExample;
import com.fosu.lesson.pojo.User;
import com.fosu.lesson.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Component
@Service(interfaceClass = UserManagerService.class)
//@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserManagerMapper userManagerMapper;

    @Override
    public String sayHello(String username) {
        return "ldsjfllsd哈哈哈";
    }

    @Override
    public List<User> qryUser() {
        qryOne();
        System.out.println("111");
        return userManagerMapper.qryUser();
    }

    @Autowired
    private TScheduleMapper tScheduleMapper;

    public List<TSchedule> qryOne() {
        TScheduleExample tScheduleExample = new TScheduleExample();
        System.out.println("*********************************************");
        tScheduleExample.createCriteria().andClassIdEqualTo("101");
        List<TSchedule> list= tScheduleMapper.selectByExample(tScheduleExample);
        System.out.println("===================================================");
        for (TSchedule t:list){
            System.out.println(t.toString());
        }
        System.out.println("===================================================");
        return list;
    }

}
