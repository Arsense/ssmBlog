package com.we.weblog.service.user;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.User;
import com.we.weblog.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Clay
 * @date 2019/3/28 15:11
 */
public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void userLoginByName() throws Exception {
        userService.userLoginByName("1","1");
    }

    @Test
    public void findUser() {
        userService.findUser();

    }

    @Test
    public void saveByUser() {
        userService.saveByUser(new User());
    }

    @Test
    public void updateUserLoginLast() {
        userService.updateUserLoginLast(new Date());
    }

    @Test
    public void updateUserLoginError() {
        userService.updateUserLoginError();
    }

    @Test
    public void updateUserNormal() {
        userService.updateUserNormal();
    }

    @Test
    public void findByUserIdAndUserPass() {
        userService.findByUserIdAndUserPass("","");
    }

    @Test
    public void userLoginByEmail() {
        userService.updateUserLoginError();
    }

    @Test
    public void updateUserLoginEnable() {
        userService.updateUserLoginError();
    }
}