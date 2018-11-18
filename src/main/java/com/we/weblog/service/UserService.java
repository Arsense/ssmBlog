package com.we.weblog.service;


import com.we.weblog.domain.User;

import java.util.Date;

/**
 * @author tangwei
 * @date 2018/10/23 19:35
 */
public interface UserService {

     /**
      * 判断用户名和密码
      *
      * @param username
      * @param password
      * @return
      * @throws Exception
      */
     boolean checkLogin(String username,String password) throws Exception;


     /**
      * 保存个人资料
      *
      * @param user user
      */
     void saveByUser(User user);


     /**
      * 修改最后登录时间
      *
      * @param lastDate 最后登录时间
      * @return User
      */
     User updateUserLoginLast(Date lastDate);

     /**
      * 增加登录错误次数
      *
      * @return 登录错误次数
      */
     Integer updateUserLoginError();

     /**
      * 修改用户的状态为正常
      *
      * @return User
      */
     User updateUserNormal();

     /**
      * 根据用户编号和密码查询
      * @param userId
      * @param newPassword
      * @return
      */
     User findByUserIdAndUserPass(String userId,String newPassword);



}
