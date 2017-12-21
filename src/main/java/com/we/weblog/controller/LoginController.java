package com.we.weblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@EnableAutoConfiguration注释，此注释自动载入应用程序所需的所有Bean
@Controller
public class LoginController {




    @RequestMapping("/main")
    public void check_login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.sendRedirect("/main.html");

    }
}
