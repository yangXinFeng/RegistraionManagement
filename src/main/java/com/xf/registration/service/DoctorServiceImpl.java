package com.xf.registration.service;

import com.xf.registration.dao.DoctorMapper;
import com.xf.registration.pojo.Doctor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    Logger logger = Logger.getLogger(DoctorServiceImpl.class);

    @Resource
    DoctorMapper doctorMapper;

    @Override
    public List<Doctor> queryAllDoctor() {
        List<Doctor> list = doctorMapper.selectAllDoctor();
        logger.info("how many doctor: "+list.size());
        return list;
    }
}
