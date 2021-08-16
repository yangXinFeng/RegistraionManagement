package com.xf.registration.service;

import com.xf.registration.pojo.Doctor;

import java.util.Date;
import java.util.List;

public interface DoctorService {

    List<Doctor> queryAllDoctor();

    List<Doctor> queryDoctorByPartAndDate(String partCode, Date date);

    List<Doctor> queryDoctorByName(String name, Date date);
    List<Doctor> queryDoctorByName(String name);

    int schedule(int doctorId,Date date,int[] num);

    int addDoctor(Doctor doctor);

    int deleteDoctor(int doctorId);

    int updateDoctor(Doctor doctor);

    int updatePassword(int doctorId, String oldPassword, String newPassword);

    String getPassword(String phone);
}
