package com.we.weblog.service;

import com.we.weblog.domain.User;

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


}
