package com.xf.registration.service;

import com.xf.registration.pojo.Patient;
import com.xf.registration.pojo.Register;

import java.util.Date;
import java.util.List;

public interface RegisterService {

    int register(int doctorId, int patientId, Date date, int workTime);

    List<Patient> queryPatientByDoctorAndDate(int doctorId, Date date);

    List<Register> queryDoctorByPatient(int patientId);
}
