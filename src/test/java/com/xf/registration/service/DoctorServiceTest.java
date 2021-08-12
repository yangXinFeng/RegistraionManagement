package com.xf.registration.service;

import com.xf.registration.pojo.Doctor;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServiceTest {

    Logger logger = Logger.getLogger(DoctorServiceTest.class);
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    DoctorService doctorService = (DoctorService) context.getBean("doctorService");

    @Test
    void queryAllDoctor() {
        List<Doctor> list = doctorService.queryAllDoctor();
        for(Doctor doctor:list){
            logger.info(doctor);
        }

    }
}