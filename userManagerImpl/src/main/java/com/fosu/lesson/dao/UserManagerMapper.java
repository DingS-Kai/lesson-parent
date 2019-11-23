package com.fosu.lesson.dao;

import com.fosu.lesson.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserManagerMapper {

    public List<User> qryUser();
}
