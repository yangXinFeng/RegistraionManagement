package com.xf.registration.dao;

import com.xf.registration.pojo.Doctor;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DoctorMapper {

    @Select("select * from doc_doctor")
    List<Doctor> selectAllDoctor();
}
