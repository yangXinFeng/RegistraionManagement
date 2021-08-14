package com.xf.registration.service;

import com.xf.registration.pojo.Doctor;

import java.util.Date;
import java.util.List;

public interface DoctorService {

    List<Doctor> queryAllDoctor();

    List<Doctor> queryDoctorByPartAndDate(String partCode, Date date);
}
