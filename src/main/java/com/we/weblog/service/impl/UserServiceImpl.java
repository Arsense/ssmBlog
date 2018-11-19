package com.we.weblog.service.impl;


import com.we.weblog.domain.User;
import com.we.weblog.mapper.UserMapper;
import com.we.weblog.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public User userLoginByName(String username, String password) throws Exception {
        return userMapper.selectByPassAndName(username,password);

    }

    /**
     * 获取管理员用户
     * @return
     */
    @Override
    public User findUser() {
        List<User> users = userMapper.findAllUsers();
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return new User();
        }
    }

    @Override
    public void saveByUser(User user) {
        userMapper.saveByUser(user);
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

    @Override
    public User userLoginByEmail(String userEmail, String password) {
        return userMapper.findByUserEmailAndPassword(userEmail, password);

    }

    @Override
    public void updateUserLoginEnable(String enable) {
        User user = this.findUser();
        user.setLoginEnable(enable);
        userMapper.saveByUser(user);
    }


}
