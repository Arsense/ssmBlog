package com.we.weblog.service;

import com.we.weblog.domain.User;

/**
 * @author tangwei
 * @date 2018/10/23 19:35
 */
public interface UserService {



     boolean checkLogin(String username,String password) throws Exception;


}
