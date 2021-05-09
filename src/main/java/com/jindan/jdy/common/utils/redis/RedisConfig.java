package com.jindan.jdy.common.utils.redis;


import com.jindan.jdy.common.utils.redis.serializer.JsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * description: Redis配置
 * date: 2020/4/5 19:23
 * author: xbdyilin
 */
@Configuration
public class RedisConfig {
    @Resource
    private RedisConnectionFactory factory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JsonRedisSerializer<>(Object.class));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JsonRedisSerializer<>(Object.class));
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }


}