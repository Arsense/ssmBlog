package com.we.weblog.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class BlogController {



    //博客的增删改查


    @GetMapping("/bloglist")
    public void showBlogs(@PathVariable int page, HttpServletResponse response) throws IOException {
        int a= 1;
        response.sendRedirect("admin/add_article.html");
    }


}
