package com.we.weblog.service.Impl;

import com.we.weblog.domain.User;
import com.we.weblog.mapping.UserMapper;
import com.we.weblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private UserMapper userMapper;

    @Autowired
    public  UserServiceImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }


    @Override
    public User getUserAllInformation() {
        return null;
    }

    @Override
    public void addBlog() {

    }

    @Override
    public boolean checkLogin(String username) throws Exception {
        User user  = userMapper.searchUserByName(username);

        if(user.getPassword().equals("admin") && user.getUserName().equals("admin")){
            return true;
        }

        return false;

    }


}
