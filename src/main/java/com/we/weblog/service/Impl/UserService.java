package com.we.weblog.service.Impl;

import com.we.weblog.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    //TODO 获取User所有信息
   User getUserAllInformation();
   public void addBlog();
   public boolean checkLogin(String username,String password) throws Exception;

   public void addSession(HttpServletRequest request,String username);

   public void destorySession(HttpServletRequest request);




}
