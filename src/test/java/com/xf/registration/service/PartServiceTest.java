package com.xf.registration.service;

import com.xf.registration.pojo.Part;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartServiceTest {

    Logger logger = Logger.getLogger(PartServiceTest.class);
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    PartService partService = (PartService) context.getBean("partService");
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    void queryAllPart() {
        List<Part> list = partService.queryAllPart();
        for(Part part : list){
            logger.info(part.toString());
        }
    }


    @Test
    void test(){
        Part part = new Part("0007","设科","设备科");
        partService.addPart(part);

        queryAllPart();

        part.setName("设备科");
        partService.updatePart(part);

        queryAllPart();

        partService.deletePart(part.getPartCode());

        queryAllPart();
    }
}