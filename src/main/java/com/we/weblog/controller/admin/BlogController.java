package com.we.weblog.controller.admin;


import com.we.weblog.TableData;
import com.we.weblog.UIModel;
import com.we.weblog.controller.IndexController;
import com.we.weblog.domain.Blog;
import com.we.weblog.service.Impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private BlogServiceImpl blogService;

    @Autowired
    public BlogController(BlogServiceImpl blogService){
        this.blogService = blogService;
    }


    @GetMapping("/delete")
    public  void deleteBlog(HttpServletRequest request,HttpServletResponse response){
        String userId = request.getParameter("id");

    }


    /**
     * 添加博客的表单控制器
      * @param blog 表单中提交的博客信息,包括标题，标签，md页面，和md转成的html页面
     * @return
     */
    @PostMapping("/post.action")
    public void postAction(@ModelAttribute("formName")Blog blog,HttpServletResponse response) throws IOException {
            blogService.addBlog(blog);
            response.sendRedirect("/admin/show.html");
    }






}
