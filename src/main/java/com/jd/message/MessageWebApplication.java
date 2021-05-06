package com.jd.message;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDubbo
public class MessageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageWebApplication.class, args);
    }
}
