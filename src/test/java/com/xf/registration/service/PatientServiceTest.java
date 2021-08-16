package com.xf.registration.service;

import com.xf.registration.pojo.Patient;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jnlp.ClipboardService;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientServiceTest {

    Logger logger = Logger.getLogger(PatientServiceTest.class);
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    PatientService patientService = (PatientService) context.getBean("patientService");
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");


    void queryAllPatient(int s, int l) {
        List<Patient> list = patientService.queryAllPatient(s,l);
        for(Patient patient : list){
            logger.info(patient.toString());
        }
    }

    void testQueryAllPatient(String name) {
        List<Patient> list = patientService.queryAllPatient(name,0,5);
        for(Patient patient : list){
            logger.info(patient.toString());
        }
    }



    @Test
    void test() {
//        queryAllPatient(0,3);
//        queryAllPatient(1,2);
        queryAllPatient(0,10);

//        Patient patient = new Patient(-1,"海绵宝宝","13431336578","南海","123456");
//
//        patientService.addPatient(patient);
//        queryAllPatient(0,10);
////
//        patient = patientService.queryAllPatient("海绵宝宝",0,5).get(0);
//
//        patient.setAddress("东海海底");
//        patientService.updatePatient(patient);
//        testQueryAllPatient("海");
//
//        patientService.updatePassword(patient.getId(),"123456","654123");
//        testQueryAllPatient("海");
//
//        logger.info(patientService.getPassword(patient.getPhone()));
//
//        patientService.deletePatient(patient.getId());
    }
}