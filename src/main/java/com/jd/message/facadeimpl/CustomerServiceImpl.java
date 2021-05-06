package com.jd.message.facadeimpl;

import com.jd.message.dao.CustomerDao;
import com.jd.message.entity.Customer;
import com.jd.message.facade.CustomerService;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 13348 on 2021/4/14.
 */
@Service(interfaceClass = CustomerService.class)
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDao customerDao;

    /**
     * 添加用户信息
     * @param customer
     * @return
     */
    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    @Override
    public List<Customer> selectCustomer(String name) {
        return customerDao.selectCustomer(name);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    @Override
    public int deleteCustomer(int id) {
        return customerDao.deleteCustomer(id);
    }
}
