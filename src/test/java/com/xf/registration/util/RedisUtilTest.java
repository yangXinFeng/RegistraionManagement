package com.xf.registration.util;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class RedisUtilTest {

    Logger logger = Logger.getLogger(RedisUtilTest.class);
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");

    @Test
    void test(){
        String key = "hash";
        String item = "test";
        boolean b;
        double d;
        b = redisUtil.hset(key,item,"4");
        d = redisUtil.hdecr(key, item, 1);
//        b = redisUtil.set(key,item);
//        d = redisUtil.decr(key,1);
        System.out.println(b+" "+d);
    }

}