package com.xf.registration.service;

import com.xf.registration.pojo.Doctor;
import com.xf.registration.vo.MonthSchedule;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DoctorService {

    List<Doctor> queryAllDoctor();

    List<Doctor> queryDoctorByPartAndDate(String partCode, Date date);

    List<Doctor> queryDoctorByName(String name, Date date);
    List<Doctor> queryDoctorByName(String name);

    Doctor queryDoctorById(int doctorId);

    int schedule(int doctorId,Date date,int[] num);

    int addDoctor(Doctor doctor);

    int deleteDoctor(int doctorId);

    int updateDoctor(Doctor doctor);

    int updatePassword(int doctorId, String oldPassword, String newPassword);

    Map<String,Object> getPassword(String phone);

    List<MonthSchedule> querySchedules(int doctorId, Date startDate, Date endDate);
}
