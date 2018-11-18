package com.we.weblog.service.impl;


import com.we.weblog.domain.User;
import com.we.weblog.mapper.UserMapper;
import com.we.weblog.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public boolean checkLogin(String username,String password) throws Exception {
       int result;
       result = userMapper.selectByPassAndName(username,password);
       if (result > 0) {
           return  true;
       }
       return false;
    }

    @Override
    public void saveByUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User updateUserLoginLast(Date lastDate) {
        return null;
    }

    @Override
    public Integer updateUserLoginError() {
        return null;
    }

    @Override
    public User updateUserNormal() {
        return null;
    }

    @Override
    public User findByUserIdAndUserPass(String userId, String password) {
        return userMapper.findByUserIdAndPassword(userId, password);
    }


}
