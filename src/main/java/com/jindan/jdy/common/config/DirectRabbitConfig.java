package com.jindan.jdy.common.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DirectRabbitConfig {

    //创建队列
    @Bean
    public Queue queue() {
// 创建一个队列路由键为hello
        return new Queue("hello");
    }
    //创建交换器
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }
    //绑定交换器和路由键
    @Bean
    public Binding bindingExchange() {
        return BindingBuilder.bind(queue()).to(directExchange()).with("hello");
    }




}
