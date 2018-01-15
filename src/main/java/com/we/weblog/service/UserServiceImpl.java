package com.we.weblog.service;

import com.we.weblog.domain.User;
import com.we.weblog.mapping.UserMapper;
import com.we.weblog.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public boolean checkLogin(String username,String password) throws Exception {
       int result = 0;

       result = userMapper.selectByPassAndName(username,password);
       if(result > 0){
           return  true;
       }
       return false;


    }

    @Override
    public void addSession(HttpServletRequest request, String username) {
        HttpSession session = request.getSession(true);
        session.setAttribute("login_user",username);
        session.setMaxInactiveInterval(600);
    }

    @Override
    public void destorySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("login_user");
    }


}
