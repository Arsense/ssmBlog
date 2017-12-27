package com.we.weblog.controller.admin;


import com.we.weblog.controller.IndexController;
import com.we.weblog.domain.Blog;
import com.we.weblog.service.Impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private BlogServiceImpl blogService;

    @Autowired
    public BlogController(BlogServiceImpl blogService){
        this.blogService = blogService;
    }

    //博客的增删改查



    @GetMapping("/bloglist")
    public void showBlogs(HttpServletResponse response) throws IOException {

        IndexController.tempBlogs = blogService.showBlogs(1);

        response.sendRedirect("/admin/show.html");
    }

    @GetMapping("/delete")
    public  void deleteBlog(HttpServletRequest request,HttpServletResponse response){
        String userId = request.getParameter("id");



    }


}
