package com.we.weblog.service;

import com.we.weblog.domain.User;

public interface UserService {

    //TODO 获取User所有信息
   User getUserAllInformation();
   public void addBlog();
   public boolean checkLogin(String username) throws Exception;






}
