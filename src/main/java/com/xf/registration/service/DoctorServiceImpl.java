package com.xf.registration.service;

import com.xf.registration.dao.DoctorMapper;
import com.xf.registration.dao.RegisterMapper;
import com.xf.registration.dao.ScheduleMapper;
import com.xf.registration.pojo.Doctor;
import com.xf.registration.pojo.Register;
import com.xf.registration.pojo.Schedule;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    Logger logger = Logger.getLogger(DoctorServiceImpl.class);

    @Resource
    DoctorMapper doctorMapper;
    @Resource
    ScheduleMapper scheduleMapper;
    @Resource
    RegisterMapper registerMapper;

    @Override
    public List<Doctor> queryAllDoctor() {
        List<Doctor> list = doctorMapper.selectAllDoctor();
        logger.info("how many doctor: "+list.size());
        return list;
    }

    @Override
    public List<Doctor> queryDoctorByPartAndDate(String partCode, Date date){
        List<Doctor> list = doctorMapper.selectDoctorByPart(partCode);
        logger.info("doctor size: "+list.size());
        for(Doctor doctor : list){
            int doctorId = doctor.getId();
            int[] num = new int[3];
            int[] count = new int[3];

            logger.info("doctorId: "+doctorId);
            Schedule schedule = scheduleMapper.selectSchedule(doctor.getId(),date);
            if (schedule != null) {
                num[0] = schedule.getNum1();
                num[1] = schedule.getNum2();
                num[2] = schedule.getNum3();
                logger.info("schedule:"+num[0]+" "+num[1]);
            }
            doctor.setScheduleRegister(num);

            for(int i=0;i<3;i++)
                count[i] = num[i]-registerMapper.countRegisterByDoctorAndDate(doctorId,date,i+1);
            logger.info("count:"+count[0]+" "+count[1]);
            doctor.setAvailableRegister(count);
        }
        return list;
    }
}
