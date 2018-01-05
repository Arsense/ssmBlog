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
    private int setUpdateId = 0;

    @Autowired
    public BlogController(BlogServiceImpl blogService){
        this.blogService = blogService;
    }


    @GetMapping("/delete")
    public  void deleteBlog(@RequestParam int deleteId,HttpServletResponse response) throws IOException {
        int id = deleteId;
        blogService.deleteBlogById(id);
        //response.sendRedirect("/admin/show.html");
    }

    @GetMapping("/toupdate")
    public  void updateBlog(@RequestParam int updateId,HttpServletResponse response) throws IOException {
        setUpdateId = updateId;
        //查找博客 md显示 标签还要处理吗？
        response.sendRedirect("/admin/edit.html");
    }

     @GetMapping("/update_send_data")
     @ResponseBody
     public  Blog updateData(){
         return blogService.getBlogById(setUpdateId);

     }

    /**
     * 添加博客的表单控制器
      * @param blog 表单中提交的博客信息,包括标题，标签，md页面，和md转成的html页面
     * @return
     */
    @PostMapping("/send")
    public void postAction(@ModelAttribute("blogFrom")Blog blog,HttpServletResponse response) throws IOException {
            blogService.addBlog(blog);
            response.sendRedirect("/admin/show.html");
    }






}
