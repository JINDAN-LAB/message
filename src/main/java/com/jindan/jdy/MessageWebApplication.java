package com.jindan.jdy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableAsync
@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.jindan.jdy.common.mapper")
@ComponentScan("com.jindan")
public class MessageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageWebApplication.class, args);
    }
}
