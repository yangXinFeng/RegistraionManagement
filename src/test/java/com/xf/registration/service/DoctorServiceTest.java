package com.xf.registration.service;

import com.xf.registration.pojo.Doctor;
import com.xf.registration.pojo.Part;
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
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void queryAllDoctor() {
        List<Doctor> list = doctorService.queryAllDoctor();
        for(Doctor doctor:list){
            logger.info(doctor);
        }

    }

    @Test
    void queryDoctorByPartAndDate() throws ParseException {

        Date date = new Date();//ft.parse("2018-1-22");
//        Date date = new Date(2018-1900,1-1,22);
        logger.info(date.toString());
        List<Doctor> list = doctorService.queryDoctorByPartAndDate("00010001",date);
        for(Doctor doctor:list){
            logger.info(doctor);
        }

        list = doctorService.queryDoctorByPartAndDate("00010001",date);
        for(Doctor doctor:list){
            logger.info(doctor);
        }
    }

    @Test
    void queryDoctorByName(){
        Date date = new Date();
        List<Doctor> list = doctorService.queryDoctorByName("罗翔");
        for(Doctor doctor:list){
            logger.info(doctor);
        }
    }

    @Test
    void schedule() throws ParseException {
        Date date = new Date();
        logger.info(date.toString());
        int res = doctorService.schedule(1,date,new int[]{8,10,0});
        logger.info(res);
    }

    @Test
    void addDoctor() {
        Doctor doctor = new Doctor(-1,"罗翔","擅长各种重大疾病",20,"luoxiang.png",1,"1234@163.com",
                "18302005578","北京市朝阳区",new Date(),new Part("00040001"),"副主任");
        int res = doctorService.addDoctor(doctor);
        logger.info(res);

    }

    @Test
    void deleteDoctor() {
        Doctor doctor = doctorService.queryDoctorByName("罗翔").get(0);
        int res = doctorService.deleteDoctor(doctor.getId());
        logger.info(res);
    }

    @Test
    void updateDoctor() {
        Doctor doctor = doctorService.queryDoctorByName("罗翔").get(0);
        doctor.setDept("医师");
        int res = doctorService.updateDoctor(doctor);
        logger.info(res);

    }

    @Test
    void updatePassword() {

        Doctor doctor = doctorService.queryDoctorByName("罗翔").get(0);
        int res = doctorService.updatePassword(doctor.getId(),"123456","654321");
        logger.info(res);

    }

}