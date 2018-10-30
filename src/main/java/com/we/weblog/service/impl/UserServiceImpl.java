package com.we.weblog.service.impl;


import com.we.weblog.mapper.UserMapper;
import com.we.weblog.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


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


}
