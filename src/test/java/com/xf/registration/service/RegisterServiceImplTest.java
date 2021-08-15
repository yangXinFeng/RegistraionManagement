package com.xf.registration.service;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceImplTest {

    Logger logger = Logger.getLogger(DoctorServiceTest.class);
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    RegisterService registerService = (RegisterService) context.getBean("registerService");
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void register() {
        Date date = new Date();
        logger.info(date.toString());
        int res = registerService.register(1,3,date,2  );
        logger.info(res);
    }
}