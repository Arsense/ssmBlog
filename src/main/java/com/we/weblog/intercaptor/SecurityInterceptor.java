package com.we.weblog.intercaptor;


import com.we.weblog.controller.IndexController;
import com.we.weblog.domain.Blog;
import com.we.weblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用户登录后台管理的拦截器
 * 存在该用户session则允许通过，否则返回登录页面
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor{


    private UserService userService;

    @Autowired
    public  SecurityInterceptor(UserService userService){
        this.userService = userService;
    }
    /**
     * 拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在这里处理拦截请求
        Object obj = request.getSession().getAttribute("login_user");
        if(obj == null){
            // response.sendRedirect(request.getContextPath()+"/login");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(username!=null && password != null) {
                boolean result = userService.checkLogin(username,password);
                if (result) {
                    IndexController.loginStatus = true;
                    userService.addSession(request,username);
//                    IndexController.url = "http://localhost:8002/admin/main.html";
                   // response.sendRedirect("/admin/main.html");
                    return true;
                }else {
                    IndexController.url = "http://localhost:8002/login?reuslt=fail";
                }

            }
            IndexController.loginStatus = false;
            return false;
        }
        return true;
    }


    /**
     * 拦截post请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
            int a =1;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        int a = 1;
    }



}
