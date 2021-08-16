package com.xf.registration.service;

import com.xf.registration.pojo.User;


public interface UserService {

    int updateUser(User user);

    int updatePassword(String loginName, String oldPassword, String newPassword);

    String getPassword(String loginName);
}
