package com.xf.registration.service;

import com.xf.registration.dao.RegisterMapper;
import com.xf.registration.dao.ScheduleMapper;
import com.xf.registration.pojo.Doctor;
import com.xf.registration.pojo.Patient;
import com.xf.registration.pojo.Register;
import com.xf.registration.util.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService{

    Logger logger = Logger.getLogger(RegisterServiceImpl.class);
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RegisterMapper registerMapper;
    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    public int register(int doctorId, int patientId, Date date, int workTime) {
        //0:fail, there are no schedule for that doctor at that date and time;
        //1:fail, patient already register that doctor at that work time;
        //2:database fail
        //3:success, add register log and update available registration at redis cache;
        int res = 0;

        Register register = new Register();
        register.setDoctor(new Doctor(doctorId));
        register.setPatient(new Patient(patientId));
        register.setDate(date);
        register.setWorkTime(workTime);

        // it should have a transaction bellow
        if(scheduleMapper.selectSchedule(doctorId,date) == null){
            res = 0;
            logger.info("register fail, there are no schedule for that doctor at that date and time;");
        }else if(registerMapper.countRegister(register) != 0){
            res = 1;
            logger.info("register fail, patient already register that doctor at that work time;");
        }else if(registerMapper.insertRegister(register) == 0) {
            res = 2;
            logger.info("register database fail");
        }else{
            res = 3;
            String key = "DoctorRegister:"+doctorId+":"+ft.format(date);
            logger.info("register success, add register log");
            if(redisUtil.hasKey(key)) {
                redisUtil.hdecr(key, "available:" + (workTime - 1), 1);
                logger.info("register success, update available registration at redis cache;");
            }

        }


        return res;

    }
}
