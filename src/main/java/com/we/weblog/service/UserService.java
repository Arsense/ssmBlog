package com.we.weblog.service;

import com.we.weblog.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }





    public boolean checkLogin(String username,String password) throws Exception {
       int result = 0;
       result = userMapper.selectByPassAndName(username,password);
       if(result > 0){
           return  true;
       }
       return false;

    }



}
