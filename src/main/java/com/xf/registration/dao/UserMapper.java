package com.xf.registration.dao;

import com.xf.registration.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Update("update doc_user set NAME = #{name},SEX = #{sex},EMAIL = #{email}, PHONE = #{phone}, ADDRESS = #{address}," +
            " ROLE = #{role} where ID = #{id}")
    int updateUser(User user);

    @Update("update doc_user set PASS_WORD = #{newPassword} where LOGIN_NAME = #{loginName}")
    int updatePassword(@Param("loginName") String loginName, @Param("newPassword") String newPassword);

    @Select("Select PASS_WORD from doc_user where LOGIN_NAME = #{loginName}")
    String getPassword(String loginName);
//    String getPassword(@Param("id") int patientId);
}
