package com.xf.registration.dao;

import com.xf.registration.pojo.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DoctorMapper {

//    @Select("select * from doc_doctor d join doc_part p on d.PART_CODE = p.PART_CODE")
    List<Doctor> selectAllDoctor();
    List<Doctor> selectAllDoctor(String name);
    Doctor selectDoctorByPrimaryKey(int id);

    @Select("select * from doc_doctor where part_code = #{partCode}")
    List<Doctor> selectDoctorByPart(String partCode);

    @Insert("insert into doc_doctor(NAME, info, price, image, SEX, EMAIL, PHONE, ADDRESS, CREATE_DATE, PART_CODE, dept)\n" +
            " values (#{name},#{info},#{price},#{image},#{sex},#{email},#{phone},#{address},#{createDate},#{partCode},#{dept});")
    int insertDoctor(Doctor doctor);

    @Update("update doc_doctor set NAME = #{name}, info = #{info}, price=#{price}, image=#{image}," +
            " SEX=#{sex}, EMAIL=#{email}, PHONE=#{phone}, ADDRESS=#{address}, CREATE_DATE=#{createDate}," +
            " PART_CODE=#{partCode}, dept=#{dept})\n" +
            " where ID = #{id}")
    int updateDoctor(Doctor doctor);
}
