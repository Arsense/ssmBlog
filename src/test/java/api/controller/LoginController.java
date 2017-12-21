package api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//@EnableAutoConfiguration注释，此注释自动载入应用程序所需的所有Bean
@Controller
public class LoginController {


    @RequestMapping("/main")
    public String checkLogin(){
        return "login";
    }


}
