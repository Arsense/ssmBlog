package com.we.weblog.service.Impl;

import com.we.weblog.dao.UserDao;
import com.we.weblog.domain.User;
import com.we.weblog.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private static UserDao userDao;

    static {
        try {
            userDao = new UserDao();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        User user  = userDao.searchUserByName(username);

        if(user.getPassword().equals("admin888") && user.getUserName().equals("admin")){
            return true;
        }

        return false;

    }


}
