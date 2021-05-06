package com.jd.message.dao.impl;

import com.jd.message.dao.CustomerDao;
import com.jd.message.entity.Customer;
import com.jd.message.util.DaoUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 13348 on 2021/4/13.
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

    private static final String SQL_ID = "CustomerMapper.%s";

    @Autowired
    @Qualifier("sessionFactory")
    private SqlSessionFactory sessionFactory;

    @Override
    public int addCustomer(Customer customer) {
        return DaoUtils.getSqlSession(sessionFactory).insert(String.format(SQL_ID, "addCustomer"),customer);
    }

    @Override
    public List<Customer> selectCustomer(String name) {
        return DaoUtils.getSqlSession(sessionFactory).selectList(String.format(SQL_ID, "selectCustomer"),name);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return DaoUtils.getSqlSession(sessionFactory).update(String.format(SQL_ID, "updateCustomer"),customer);
    }

    @Override
    public int deleteCustomer(int id) {
        return DaoUtils.getSqlSession(sessionFactory).delete(String.format(SQL_ID, "deleteCustomer"),id);
    }
}
