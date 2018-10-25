package com.we.weblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <pre>
 *     前端跳转资源控制  先让其跑起来
 * </pre>
 *
 * @author tangwei
 * @date 2018/10/25 9:40
 */
@Controller
public class ThemeIndexController extends BaseController {

    /**
     * 首页视图
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "/index";
    }

    /**
     * 首页视图
     * @return
     */
    @GetMapping("/login1")
    public String login(){
        return "/login1";
    }

    /**
     * 首页视图
     * @return
     */
    @GetMapping("/article")
    public String article(){
        return "/article";
    }

    /**
     * 首页视图
     * @return
     */
    @GetMapping("/tags")
    public String tags(){
        return "/tags";
    }


    @GetMapping("/about")
    public String about(){
        return "/about";
    }


    /**
     * 首页视图
     * @return
     */
    @GetMapping("/archive")
    public String archive(){
        return "/archive";
    }

    /**
     * 首页视图
     * @return
     */
    @GetMapping("/category")
    public String category(){
        return "/category";
    }


}
