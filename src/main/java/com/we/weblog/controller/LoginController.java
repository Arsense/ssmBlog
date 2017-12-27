package com.we.weblog.controller;

import com.we.weblog.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@EnableAutoConfiguration注释，此注释自动载入应用程序所需的所有Bean
@Controller
public class LoginController {


    private  static UserServiceImpl userService;

    @Autowired
    public LoginController(UserServiceImpl userService){
        this.userService = userService;
    }
    /**
     * 登陆捕获
     * @param request
     * @param response
     * @throws IOException
     */
//    @PostMapping("/login")
    @PostMapping("/adminer")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = (String) request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            //先不文字回显示
            return;
        }

        boolean result = userService.checkLogin(username);
        if (result) {
            response.sendRedirect("/main.html");
        }else {
            response.sendRedirect("/login.html");
        }
    }
}
