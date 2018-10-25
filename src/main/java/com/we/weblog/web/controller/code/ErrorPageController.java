package com.we.weblog.web.controller.code;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  全局异常处理
 */
@Controller
@RequestMapping("/error")
public class ErrorPageController implements ErrorController {

    @Override
    public String getErrorPath() {
        //直接返回错误界面吧 先不多做处理
        return "404";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
//    /**
//     * 渲染404页面
//     *
//     * @return String
//     */
//    @GetMapping(value = "/404")
//    public String fourZeroFour() {
//        return "404";
//    }
}
