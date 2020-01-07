package com.yc.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: yinchao
 * @date 2019/7/1
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // key的序列化类型
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // value的序列化类型
        redisTemplate.setValueSerializer(new RedisObjectSerializer());
        return redisTemplate;
    }
}
