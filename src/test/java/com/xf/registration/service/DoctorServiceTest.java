package com.xf.registration.service;

import com.xf.registration.pojo.Doctor;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
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

    @Test
    void queryDoctorByPartAndDate() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ft.parse("2018-1-22");
//        Date date = new Date(2018-1900,1-1,22);
        logger.info(date.toString());
        List<Doctor> list = doctorService.queryDoctorByPartAndDate("00010001",date);
        for(Doctor doctor:list){
            logger.info(doctor);
        }
    }
}