package com.we.weblog.controller;


import com.we.weblog.domain.Blog;
import com.we.weblog.domain.YearBlog;
import com.we.weblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 *   前端页面显示的控制器
 */
@Controller
public class FrontController {


    private BlogService blogService;


    public FrontController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping("//yearblogs/{page}")
    @ResponseBody
    public List<YearBlog>  getYearBlogs(@PathVariable int page, HttpServletResponse response) throws IOException {
        List<YearBlog> blogList = blogService.getYearBlog(page);
        int nblog = blogService.getNumberOfYearBlog();
        return  blogList ;
    }


}
