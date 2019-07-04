package com.we.weblog.service.impl;


import com.we.weblog.domain.User;
import com.we.weblog.mapper.UserMapper;
import com.we.weblog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public User userLoginByName(String username, String password) throws Exception {
        User user  = new User();
        user.setPassword(password);
        user.setUserName(username);
        List<User> allUser = null;
        try {
            //todo 各种加打印的日志
            allUser = userMapper.queryUser(user);
            if (CollectionUtils.isEmpty(allUser)) {
                throw new RuntimeException("用户存在异常, 请创建用户");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return allUser.get(0);
    }
    /**
     * 获取管理员用户
     * @return
     */
    @Override
    public User findUser() {
        List<User> users = userMapper.queryUser(new User());
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
        User user = null;
        try {
            user = this.findUser();
            user.setLoginLast(lastDate);
            userMapper.saveByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Integer updateUserLoginError() {
        User user = this.findUser();
        user.setLoginError((user.getLoginError() == null ? 0 : user.getLoginError()) + 1);
        try {
            userMapper.saveByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user.getLoginError();
    }

    @Override
    public User updateUserNormal() {
        User user = this.findUser();
        user.setLoginEnable("true");
        user.setLoginError(0);
        user.setLoginLast(new Date());
        try {
            userMapper.saveByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByUserIdAndUserPass(String userId, String password) {
//        return userMapper.queryUser(new U);
        return null;
    }

    @Override
    public User userLoginByEmail(String userEmail, String password) {
        return null;
//        return userMapper.findByUserEmailAndPassword(userEmail, password);
    }

    @Override
    public void updateUserLoginEnable(String enable) {
        User user = this.findUser();
        user.setLoginEnable(enable);
        try {
            userMapper.saveByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
