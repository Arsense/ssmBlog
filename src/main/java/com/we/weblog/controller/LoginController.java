package com.we.weblog.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.we.weblog.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;


//@EnableAutoConfiguration注释，此注释自动载入应用程序所需的所有Bean
@Controller
public class LoginController extends BaseController{


    private  static UserServiceImpl userService;



    @Autowired
    public LoginController(UserServiceImpl userService){
        this.userService = userService;
    }

    /**
     * 登录 （注解跳过权限验证）
     */
  //  @Login(action = Action.Skip)
    @PostMapping("/admin")
    public String doLogin() throws Exception {
        /**
         * 生产环境需要过滤sql注入
         */
        WafRequestWrapper req = new WafRequestWrapper(request);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean result = userService.checkLogin(username, password);
        if (result) {
            SSOHelper.setCookie(request, response, SSOToken.create().setIp(request).setId(1000).setIssuer(username), false);
            return redirectTo("/admin/main.html");
        }else {
            return   redirectTo("/login1.html");
        }
    }





    @GetMapping("/logout")
    public String logout() throws IOException {
        //销毁session
       // userService.destorySession(request);
        SSOHelper.clearLogin(request, response);
        return redirectTo("/index.html");

    }





     /**
     * 登录 （注解跳过权限验证）
     */
    @Login(action = Action.Skip)
    @RequestMapping("/login")
    public String login() {
        // 设置登录 COOKIE
        SSOToken st = SSOHelper.getSSOToken(request);
        if(st != null) {

            return redirectTo("/admin/main.html");
        }
        //在这里处理拦截请求
        /*
         * 登录需要跳转登录前页面，自己处理 ReturnURL 使用
         * HttpUtil.decodeURL(xx) 解码后重定向
         */
        return redirectTo("/login1.html");
    }



}
