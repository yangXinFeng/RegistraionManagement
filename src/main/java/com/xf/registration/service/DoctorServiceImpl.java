package com.xf.registration.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xf.registration.dao.DoctorMapper;
import com.xf.registration.dao.RegisterMapper;
import com.xf.registration.dao.ScheduleMapper;
import com.xf.registration.pojo.Doctor;
import com.xf.registration.pojo.Register;
import com.xf.registration.pojo.Schedule;
import com.xf.registration.util.RedisUtil;
import com.xf.registration.vo.MonthSchedule;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    Logger logger = Logger.getLogger(DoctorServiceImpl.class);
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private ScheduleMapper scheduleMapper;
    @Resource
    private RegisterMapper registerMapper;
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<Doctor> queryAllDoctor() {
        List<Doctor> list = doctorMapper.selectAllDoctor();
        logger.info("how many doctor: "+list.size());
        return list;
    }

    @Override
    public List<Doctor> queryDoctorByPartAndDate(String partCode, Date date){
        String key1 = "DoctorSet:"+partCode;
        List<Doctor> list;
        if(redisUtil.hasKey(key1)){
            String value1 = (String)redisUtil.get(key1);
            Type type = new TypeToken<List<Doctor>>() {}.getType();
            list =  new Gson().fromJson(value1,type);
            logger.info("find doctor by part from redis, doctor size: " + list.size());
        }else {
            list = doctorMapper.selectDoctorByPart(partCode);
            if(list != null) {
                logger.info("find doctor by part from mysql,doctor size: " + list.size());
                String toJson = new Gson().toJson(list);
                // 存在到缓存中
                redisUtil.set(key1, toJson, 700);
            }else logger.info("find doctor by part from mysql,doctor size: null" );
        }

        for(Doctor doctor : list){setAvailableRegisterForDoctor(doctor,date);}
        return list;
    }

    @Override
    public List<Doctor> queryDoctorByName(String name, Date date) {
        List<Doctor> list = doctorMapper.selectAllDoctor(name);
        for(Doctor doctor : list){setAvailableRegisterForDoctor(doctor,date);}

        return list;
    }

    @Override
    public List<Doctor> queryDoctorByName(String name) {
        return doctorMapper.selectAllDoctor(name);
    }

    @Override
    public Doctor queryDoctorById(int doctorId) {
        return doctorMapper.selectDoctorByPrimaryKey(doctorId);
    }

    @Override
    public int schedule(int doctorId, Date date, int[] num) {
        // 0:fail
        // 1:success
        int res = 0;
        Schedule schedule = scheduleMapper.selectSchedule(doctorId,date);

        Schedule newSchedule = new Schedule(-1,doctorId,date,num[0],num[1],num[2]);

        if(schedule == null){
            res = scheduleMapper.insertSchedule(newSchedule);
            logger.info("schedule: insert");
        }else{
            newSchedule.setId(schedule.getId());
            res = scheduleMapper.updateSchedule(newSchedule);
            logger.info("schedule: update");
        }
        String key2 = "DoctorRegister:"+doctorId+":"+ft.format(date);
        if(redisUtil.hasKey(key2)) redisUtil.del(key2);
        return res;
    }

    @Override
    public int addDoctor(Doctor doctor) {
        return doctorMapper.insertDoctor(doctor);
    }

    @Override
    public int deleteDoctor(int doctorId) {
        return doctorMapper.deleteDoctor(doctorId);
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        return doctorMapper.updateDoctor(doctor);
    }

    @Override
    public int updatePassword(int doctorId, String oldPassword, String newPassword) {
        int res = 0;
        String p = doctorMapper.getPasswordById(doctorId);
        if (!p.equals(oldPassword)){
            logger.info("updatePassword:oldPassword incorrect!");
//            logger.info("true:"+p+" false:"+oldPassword);
            res = 2;
        }else{
            res = doctorMapper.updatePassword(doctorId,newPassword);
            logger.info("updatePassword:ing...");
        }


        return res;
    }

    @Override
    public Map<String,Object> getPassword(String phone) {
        return doctorMapper.getPasswordByPhone(phone);
    }

    @Override
    public List<MonthSchedule> querySchedules(int doctorId, Date startDate, Date endDate) {
        List<MonthSchedule> res = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int s = calendar.get(Calendar.DATE);
        calendar.setTime(endDate);
        int e = calendar.get(Calendar.DATE);
        for(int day = s;day<e;day++){
            calendar.set(Calendar.DATE,day);
            Doctor doctor = setAvailableRegisterForDoctor(new Doctor(doctorId),calendar.getTime());
            int[] num = doctor.getScheduleRegister();
            int[] count = doctor.getAvailableRegister();
            if (num[0] != 0 || num[1] != 0){
                MonthSchedule monthSchedule = new MonthSchedule(doctorId,calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE) );
                monthSchedule.setSchedule(num);
                monthSchedule.setAvailable(count);
                res.add(monthSchedule);
            }
        }

//        List<Schedule> lists = scheduleMapper.selectSchedules(doctorId,startDate,endDate);
//        Calendar calendar = Calendar.getInstance();
//        for(Schedule schedule : lists){
//            calendar.setTime(schedule.getDate());
//            MouthSchedule mouthSchedule = new MouthSchedule(doctorId,calendar.get(Calendar.YEAR),
//                    calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE) );
//            int[] num = new int[3];
//            int[] count = new int[3];
//
//            Register register = new Register();
//            register.setDate(schedule.getDate());
//            register.setDoctor(new Doctor(doctorId));
//            for (int i = 0; i < 3; i++) {
//                num[i] = schedule.getNum(i + 1);
//                register.setWorkTime(i + 1);
//                count[i] = num[i] - registerMapper.countRegister(register);
//            }
//
//            mouthSchedule.setSchedule(num);
//            mouthSchedule.setAvailable(count);
//        }

        return res;
    }

    Doctor setAvailableRegisterForDoctor(Doctor doctor,Date date){

        int doctorId = doctor.getId();
        int[] num = new int[3];
        int[] count = new int[3];

        logger.info("doctorId: "+doctorId);
        String key2 = "DoctorRegister:"+doctorId+":"+ft.format(date);
        if(redisUtil.hasKey(key2)){
            Map<Object,Object> map = redisUtil.hmget(key2);
            Map<String,String> castMap = new HashMap();
            for(Object o : map.keySet()){
                castMap.put((String) o,(String) map.get(o));
            }
            for(int i=0;i<3;i++){
                num[i] = Integer.parseInt(castMap.get("schedule:"+i)) ;
                count[i] = Integer.parseInt(castMap.get("available:"+i));
            }
            logger.info("find schedule and count from redis");
        }else {
            Schedule schedule = scheduleMapper.selectSchedule(doctor.getId(), date);
            if(schedule == null) return doctor;

            Register register = new Register();
            register.setDate(date);
            register.setDoctor(doctor);
            for (int i = 0; i < 3; i++) {
                num[i] = schedule.getNum(i+1);
                register.setWorkTime(i+1);
                count[i] = num[i] - registerMapper.countRegister(register);
//                    count[i] = num[i] - registerMapper.countRegisterByDoctorAndDate(doctorId, date, i + 1);
//                    redisTemplate.opsForHash().put(key2,"schedule:"+i,num[i]);
//                    redisTemplate.opsForHash().put(key2,"available:"+i,count[i]);
                redisUtil.hset(key2,"schedule:"+i,num[i]+"");
                redisUtil.hset(key2,"available:"+i,count[i]+"");
            }
            redisUtil.expire(key2,1800);

            logger.info("find schedule and count from sql");
        }
        logger.info("schedule:" + num[0] + " " + num[1]);
        logger.info("count:" + count[0] + " " + count[1]);
        doctor.setScheduleRegister(num);
        doctor.setAvailableRegister(count);

        return doctor;

    }


}
