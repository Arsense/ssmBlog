package com.we.weblog.web.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.service.LogsService;
import com.we.weblog.service.UserService;
import com.we.weblog.tool.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;


/**
 * 登陆控制界面
 */
@Controller
public class LoginController extends BaseController{

    private UserService userService;
    private LogsService logService;

     @Autowired
     LoginController(UserService userService,LogsService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    /**
     * 登录 （注解跳过权限验证）
     */
  //  @Login(action = Action.Skip)
    @PostMapping("/login")
    public String doLogin(HttpServletRequest request) throws Exception {
        /**
         * 生产环境需要过滤sql注入  登陆验证次数校验  返回一个IP
         */
        WafRequestWrapper wafRequest = new WafRequestWrapper(request);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean result = userService.checkLogin(username, password);
        if (result) {
            //创建日志
            Log loginLog =new Log(LogActions.LOGIN,username, IpTool.getIpAddress(request),1);
            if (logService.saveByLogs(loginLog) < 0)
                throw  new Exception("loginLog add error");
            //这里创建session 防止重复登录
            SSOHelper.setCookie(request, response, SSOToken.create().setIp(request).setId(1000).setIssuer(username), false);
            return "/admin/index.html";
        } else {
            return "/login";
        }
    }


    /**
     *  登出页面
     * @return
     */
    @GetMapping("/logout")
    public String logout() {
        SSOHelper.clearLogin(request, response);
        logService.saveByLogs(new Log(LogActions.LOGOUT,null,IpTool.getIpAddress(request),1));
        return redirectTo("/index.html");
    }



     /**
     * 登录 （注解跳过权限验证）
     */
    @Login(action = Action.Skip)
    @RequestMapping("/login")
    public String login() {
        // 设置登录 COOKIE
        SSOToken ssoToken = SSOHelper.getSSOToken(request);
        if(ssoToken != null) {
            return redirectTo("/admin/index.html");
        }
        return redirectTo("/login.html");
    }



}
