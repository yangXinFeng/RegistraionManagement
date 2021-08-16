package com.xf.registration.service;

import com.xf.registration.dao.UserMapper;
import com.xf.registration.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService{

    Logger logger = Logger.getLogger(DoctorServiceImpl.class);

    @Resource
    UserMapper userMapper;

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updatePassword(String loginName, String oldPassword, String newPassword) {
        int res = 0;
        String p = userMapper.getPassword(loginName);
        if (!p.equals(oldPassword)){
            logger.info("updatePassword:oldPassword incorrect!");
            res = 2;
        }else{
            res = userMapper.updatePassword(loginName,newPassword);
            logger.info("updatePassword:ing...");
        }

        return res;
    }

    @Override
    public String getPassword(String loginName) {
        return userMapper.getPassword(loginName);
    }
}
