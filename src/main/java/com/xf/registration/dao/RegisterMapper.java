package com.xf.registration.dao;

import com.xf.registration.pojo.Patient;
import com.xf.registration.pojo.Register;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RegisterMapper {

    @Select("select * from doc_patient where id in (select user_ID from doc_register where doctor_Id = #{doctorId} and DATE = #{date});")
    List<Patient> queryPatientByDoctorAndDate(int doctorId, String date);

//    @Select("select count(*) from doc_register where doctor_Id = #{id} and DATE = #{date,jdbcType=DATE} and work_time = #{time};")
//    int countRegisterByDoctorAndDate(@Param("id")int doctorId, @Param("date")Date date, @Param("time")int workTime);
    int countRegister(Register register);

    List<Register> queryDoctorByPatient(int patientId);

    @Insert("insert into doc_register(user_id, doctor_id, date, work_time) VALUES (#{patient.id},#{doctor.id},#{date},#{workTime});")
    int insertRegister(Register register);


}
