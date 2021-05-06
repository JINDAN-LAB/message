package com.jd.message.dao;

import com.jd.message.entity.User;

import java.util.List;

/**
 * Created by 13348 on 2021/4/13.
 */
public interface UserDao {

    int addUser(User user);

    List<User> selectUser(String name);

    int updateUser(User user);

    int deleteUser(int id);

}
