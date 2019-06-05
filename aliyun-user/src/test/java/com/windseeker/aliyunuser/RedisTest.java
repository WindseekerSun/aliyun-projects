package com.windseeker.aliyunuser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testConnection() {
        redisTemplate.boundValueOps("name").set("windseeker");
    }

    @Test
    public void testGet() {
        System.out.println(redisTemplate.boundValueOps("name").get());
    }

   // @Test
    public void testZGet() {
        redisTemplate.boundZSetOps("users").getOperations().exec();
    }
}
