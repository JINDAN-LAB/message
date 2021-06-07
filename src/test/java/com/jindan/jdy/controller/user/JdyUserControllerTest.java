package com.jindan.jdy.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.jindan.jdy.common.dto.RiskWarningDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * Created by 13348 on 2021/5/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdyUserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
//        User user =new User("root","root");
//        session.setAttribute("user",user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
    }

    /**
     * 获取教程测试用例
     * @throws Exception
     */
    @Test
    public void qryLearn() throws Exception {
        RiskWarningDto riskWarningDto = new RiskWarningDto();
        riskWarningDto.setPageSize(2);
        riskWarningDto.setCurrentPage(0);
        riskWarningDto.setStartTime("2021-06-06");
        riskWarningDto.setEndTime("2021-06-07");
        mvc.perform(MockMvcRequestBuilders.post("/riskWarning/selectRiskWarning")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(riskWarningDto))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
