package com.xf.registration.service;

import com.xf.registration.pojo.User;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    Logger logger = Logger.getLogger(UserServiceTest.class);
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userService = (UserService) context.getBean("userService");
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    User user = new User(1,"admin","123456","星风",0,"1297965891@163.com",
            "110","广州白云山",1,new Date(),"1");

    @Test
    void updateUser() {

        userService.updateUser(user );
    }

    @Test
    void updatePassword() {
        userService.updatePassword("admin","123456","654321");
    }

    @Test
    void getPassword() {
        logger.info(userService.getPassword("admin"));
    }
}