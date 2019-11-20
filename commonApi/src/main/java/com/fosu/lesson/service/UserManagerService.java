package com.fosu.lesson.service;

import com.fosu.lesson.pojo.User;

import java.util.List;

public interface UserManagerService {

    public String sayHello(String student_name);

    public List<User> qryUser();

}
