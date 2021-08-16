package com.xf.registration.service;

import com.xf.registration.pojo.Patient;
import com.xf.registration.pojo.Register;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    @Test
    void queryPatientByDoctorAndDate(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        date = calendar.getTime();
        List<Patient> list = registerService.queryPatientByDoctorAndDate(1,date);
        for(Patient patient : list){
            logger.info(patient.toString());
        }

    }

    @Test
    void queryDoctorByPatient(){
        List<Register> registers = registerService.queryDoctorByPatient(2);
        for(Register register : registers){
            logger.info(register.toString());
        }
    }
}