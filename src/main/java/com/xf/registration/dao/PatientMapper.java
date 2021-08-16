package com.xf.registration.dao;

import com.xf.registration.pojo.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PatientMapper {

    @Insert("insert into doc_patient(name, phone, address, password) VALUES (#{name}, #{phone}, #{address}, #{password});")
    int insertPatient(Patient patient);

    @Delete("update doc_patient set is_valid = 0 where id = #{patientId}")
    int deletePatient(int patientId);

    @Select("select * from doc_patient where is_valid = 1 limit #{start},#{len}")
    List<Patient> selectAllPatient(@Param("start") int start, @Param("len")int len);
    //concat('%',#{name},'%')
    @Select("select * from doc_patient where NAME like concat(\"%\",#{name},\"%\") and is_valid = 1 limit #{start},#{len};")
    List<Patient> selectAllPatientByName(@Param("name")String name, @Param("start") int start, @Param("len")int len);

    @Update("update doc_patient set name = #{name}, phone = #{phone}, address = #{address} where id = #{id}")
    int updatePatient(Patient patient);

    @Update("update doc_patient set password = #{newPassword} where id = #{id}")
    int updatePassword(@Param("id") int patientId, @Param("newPassword") String newPassword);

//    String getPassword(String phone);
//    String getPassword(@Param("id") int patientId);
    @Select("Select password from doc_patient where phone = #{phone} and is_valid = 1")
    String getPasswordByPhone(String phone);
    @Select("Select password from doc_patient where id = #{doctorId}")
    String getPasswordById(int doctorId);
}
