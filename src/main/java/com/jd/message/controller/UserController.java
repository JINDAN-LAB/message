package com.jd.message.controller;

import com.alibaba.fastjson.JSON;
import com.jd.message.entity.Customer;
import com.jd.message.entity.Project;
import com.jd.message.entity.User;
import com.jd.message.facade.CustomerService;
import com.jd.message.facade.ProjectService;
import com.jd.message.facade.UserService;
import com.jd.message.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * Created by 13348 on 2021/4/13.
 */
@Controller
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;
    @Autowired
    public ProjectService projectService;
    @Autowired
    public CustomerService customerService;
    @Autowired
    public RedisUtil redisUtil;

    @RequestMapping(value = "/useradd", method = RequestMethod.GET)
    @ResponseBody
    public Object addUser() {
        User user = new User();
        user.setCustomerId(2);
        user.setId(1);
        user.setName("测试01");
//        int result = userService.addUser(user);
        int i = userService.updateUser(user);
        List<User> result = userService.selectUser("测试01");
//        int i = userService.deleteUser(1);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/customeradd", method = RequestMethod.GET)
    @ResponseBody
    public Object addCustomer() {
        Customer customer =new Customer();
        customer.setName("测试");
        customer.setProjectId(1);
        int result = customerService.addCustomer(customer);
//        int i = userService.updateUser(user);
//        List<User> result = userService.selectUser("测试01");
//        int i = userService.deleteUser(1);
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/projectadd", method = RequestMethod.GET)
    @ResponseBody
    public Object addProject() {
        Project project = new Project();
        project.setName("测试");
        int i = projectService.addProject(project);
        return JSON.toJSONString(i);
    }

    @RequestMapping(value = "/setcache", method = RequestMethod.GET)
    @ResponseBody
    public Object setCache() {
        boolean test = redisUtil.set("002", "test2");
        return JSON.toJSONString(test);
    }

    @RequestMapping(value = "/getcache", method = RequestMethod.GET)
    @ResponseBody
    public Object getCache() {
        String s = redisUtil.get("002");
        return JSON.toJSONString(s);
    }

}
