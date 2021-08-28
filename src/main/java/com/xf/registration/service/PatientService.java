package com.xf.registration.service;

import com.xf.registration.pojo.Patient;

import java.util.List;
import java.util.Map;

public interface PatientService {

    List<Patient> queryAllPatient(int start, int len);

    List<Patient> queryAllPatient(String name, int start, int len);

    Patient querryPatientById(int patientId);

    int deletePatient(int patientId);

    int addPatient(Patient patient);

    int updatePatient(Patient patient);

    int updatePassword(int patientId, String oldPassword, String newPassword);

    Map<String, Object> getPassword(String phone);

}
