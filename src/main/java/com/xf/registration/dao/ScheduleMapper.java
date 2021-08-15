package com.xf.registration.dao;

import com.xf.registration.pojo.Schedule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface ScheduleMapper {

    @Insert("insert into doc_schedule(doctor_Id, date, num1,num2, num3) VALUES (#{doctor_id}, #{date}, #{num1},#{num2}, #{num3});")
    int insertSchedule(Schedule schedule);

    @Select("select * from doc_schedule where doctor_Id = #{doctorId} and DATE = #{date,jdbcType=DATE};")
    Schedule selectSchedule(@Param("doctorId") int doctorId,@Param("date") Date date);

    @Update("update doc_register set num1 = #{num[0]}, num2 = {num[1]} ,num3 = {num[2]} where ID = #{id};")
    int updateSchedule(Schedule schedule);
}
