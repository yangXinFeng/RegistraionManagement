package com.xf.registration.dao;

import com.xf.registration.pojo.Patient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PatientMapper {

    @Insert("insert into doc_patient(name, phone, address) VALUES (#{name}, #{phone}, #{address});")
    int addPatient(Patient patient);

    @Delete("delete from doc_patient where id = #{patientId}")
    int deletePatient(int patientId);

    List<Patient> selectAllPatient();
    List<Patient> selectAllPatient(String name);

    @Update("update doc_patient set name = #{name}, phone = #{phone}, address = #{address} where id = #{id}")
    int updatePatient(Patient patient);
}
