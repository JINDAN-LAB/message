package com.jd.message.dao.impl;


import com.jd.message.dao.UserDao;
import com.jd.message.entity.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.jd.message.util.DaoUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13348 on 2021/4/13.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final String SQL_ID = "UserMapper.%s";

    @Autowired
    @Qualifier("sessionFactory")
    private SqlSessionFactory sessionFactory;

    @Override
    public int addUser(User user) {
        return DaoUtils.getSqlSession(sessionFactory).insert(String.format(SQL_ID, "addUser"), user);
    }

    @Override
    public List<User> selectUser(String name) {
        return DaoUtils.getSqlSession(sessionFactory).selectList(String.format(SQL_ID, "selectUser"), name);
    }

    @Override
    public int updateUser(User user) {
        return DaoUtils.getSqlSession(sessionFactory).update(String.format(SQL_ID, "updateUser"), user);
    }

    @Override
    public int deleteUser(int id) {
        return DaoUtils.getSqlSession(sessionFactory).delete(String.format(SQL_ID, "deleteUser"), id);
    }
}
