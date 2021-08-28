package com.xf.registration.service;

import com.xf.registration.dao.PatientMapper;
import com.xf.registration.pojo.Patient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("patientService")
public class PatientServiceImpl implements PatientService {

    Logger logger = Logger.getLogger(DoctorServiceImpl.class);

    @Resource
    PatientMapper patientMapper;

    @Override
    public List<Patient> queryAllPatient(int start, int len) {
        return patientMapper.selectAllPatient(start,len);
    }

    @Override
    public List<Patient> queryAllPatient(String name, int start, int len) {
        return patientMapper.selectAllPatientByName(name,start,len);
    }

    @Override
    public Patient querryPatientById(int patientId) {
        return patientMapper.selectPatientByPrimaryKey(patientId);
    }

    @Override
    public int deletePatient(int patientId) {
        return patientMapper.deletePatient(patientId);
    }

    @Override
    public int addPatient(Patient patient) {
        return patientMapper.insertPatient(patient);
    }

    @Override
    public int updatePatient(Patient patient) {
        return patientMapper.updatePatient(patient);
    }

    @Override
    public int updatePassword(int patientId, String oldPassword, String newPassword) {
        int res = 0;
        String p = patientMapper.getPasswordById(patientId);
        if (!p.equals(oldPassword)){
            logger.info("updatePassword:oldPassword incorrect!");
            res = 2;
        }else{
            res = patientMapper.updatePassword(patientId,newPassword);
            logger.info("updatePassword:ing...");
        }

        return res;
    }

    @Override
    public Map<String, Object> getPassword(String phone) {
        return patientMapper.getPasswordByPhone(phone);
    }
}
