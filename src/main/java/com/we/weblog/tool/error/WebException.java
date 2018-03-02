package com.we.weblog.tool.error;


import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  全局异常处理
 */
@Controller
@RequestMapping("/error")
public class WebException  implements  ErrorController {
    @Override
    public String getErrorPath() {
        //直接返回错误界面吧 先不多做处理

        return "redirect:/404.html";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }


}
