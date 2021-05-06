package com.jd.message.facadeimpl;

import com.jd.message.dao.UserDao;
import com.jd.message.entity.User;
import com.jd.message.facade.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 13348 on 2021/4/13.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> selectUser(String name) {
        return userDao.selectUser(name);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
}
