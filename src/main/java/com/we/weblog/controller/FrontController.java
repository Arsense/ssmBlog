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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *   前端页面显示的控制器
 */
@Controller
public class FrontController {


    private BlogService blogService;
    private static int postId = 0;

    public FrontController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping("/years_blog_data")
    @ResponseBody
    public List<YearBlog>  getYearBlogs(  HttpServletResponse response) throws IOException {
        int page = 1;  //先默认为1吧
        List<YearBlog> blogList = blogService.getYearBlog(page);
        int nblog = blogService.getNumberOfYearBlog();
        return  blogList ;
    }


    /**
     *  先处理好数据 要不然后让所有url 在
     * @param id
     * @return
     */
    @GetMapping("/post/{id}")
    public void post(@PathVariable String id,HttpServletResponse response ) throws IOException {
        int tempId = Integer.parseInt(id);
        postId = tempId;
        if(postId==0){
            //去错误界面 page 404
        }
        response.sendRedirect("/article.html");

    }

    @GetMapping("/post")
    @ResponseBody
    public Map<String, Blog> postData( ){
        Map<String,Blog> map  = new HashMap<>();
        Blog currentBlog = blogService.getBlogById(postId);
        Blog preBlog = blogService.getPreviousBlog(postId);
        Blog nextBlog = blogService.getNextBlog(postId);

        map.put("current",currentBlog);
        map.put("next",nextBlog);
        map.put("previous",preBlog);
        return map;
    }



}
