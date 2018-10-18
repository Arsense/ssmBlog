package com.we.weblog.service;


import com.we.weblog.domain.User;
import com.we.weblog.mapper.UserMapper;
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
       int result;
       result = userMapper.selectByPassAndName(username,password);
       if (result > 0) {
           return  true;
       }
       return false;

    }

    /**
     * 返回当前登录用户
     *
     * @return
     */
    public static User getLoginUser() {
       return new User();
    }

}
