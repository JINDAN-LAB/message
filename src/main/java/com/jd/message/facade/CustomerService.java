package com.jd.message.facade;

import com.jd.message.entity.Customer;

import java.util.List;

/**
 * Created by 13348 on 2021/4/14.
 */
public interface CustomerService {

    int addCustomer(Customer customer);

    List<Customer> selectCustomer(String name);

    int updateCustomer(Customer customer);

    int deleteCustomer(int id);
    
}
