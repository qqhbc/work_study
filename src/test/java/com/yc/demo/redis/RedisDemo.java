package com.yc.demo.redis;

import com.alibaba.fastjson.JSON;
import com.yc.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: yinchao
 * @date 2019/7/1
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisDemo {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testSet(){
        String key = "aaa";
        redisTemplate.opsForValue().set(key,"abc");

        System.out.println(redisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testGet() {
        System.out.println(this.redisTemplate.opsForValue().get("study"));
    }
    @Test
    public void testSet1() {
        User vo = new User() ;
        vo.setUserName("hty");
        vo.setAge(19);
        System.out.println(vo);
        this.redisTemplate.opsForValue().set("study", JSON.toJSON(vo));
    }
}
