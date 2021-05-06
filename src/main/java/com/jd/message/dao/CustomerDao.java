package com.jd.message.dao;

import com.jd.message.entity.Customer;

import java.util.List;

/**
 * Created by 13348 on 2021/4/13.
 */
public interface CustomerDao {

    int addCustomer(Customer customer);

    List<Customer> selectCustomer(String name);

    int updateCustomer(Customer customer);

    int deleteCustomer(int id);
}
