package com.xf.registration.dao;

import com.xf.registration.pojo.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DoctorMapper {

//    @Select("select * from doc_doctor d join doc_part p on d.PART_CODE = p.PART_CODE")
    List<Doctor> selectAllDoctor();
    List<Doctor> selectAllDoctor(String name);
    Doctor selectDoctorByPrimaryKey(int id);

    @Select("select * from doc_doctor where part_code = #{partCode} and is_valid = 1")
    List<Doctor> selectDoctorByPart(String partCode);

    @Insert("insert into doc_doctor(NAME, info, price, image, SEX, EMAIL, PHONE, ADDRESS, CREATE_DATE, PART_CODE, dept)\n" +
            " values (#{name},#{info},#{price},#{image},#{sex},#{email},#{phone},#{address},#{createDate},#{part.partCode},#{dept});")
    int insertDoctor(Doctor doctor);

    @Update("update doc_doctor set NAME = #{name}, info = #{info}, price=#{price}, image=#{image}," +
            " SEX=#{sex}, EMAIL=#{email}, PHONE=#{phone}, ADDRESS=#{address}," +
            " dept=#{dept} where ID = #{id}")
    int updateDoctor(Doctor doctor);

    @Update("update doc_doctor set password = #{newPassword} where ID = #{id}")
    int updatePassword( @Param("id") int doctorId, @Param("newPassword") String newPassword);

    @Select("Select password from doc_doctor where phone = #{phone}")
    String getPasswordByPhone(String phone);
    @Select("Select password from doc_doctor where ID = #{doctorId}")
    String getPasswordById(int doctorId);

    @Update("update doc_doctor set is_valid = 0 where ID = #{doctorId}")
    int deleteDoctor(int doctorId);
}
