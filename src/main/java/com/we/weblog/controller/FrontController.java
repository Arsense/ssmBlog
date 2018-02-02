package com.we.weblog.controller;


import com.we.weblog.domain.Context;
import com.we.weblog.domain.YearBlog;
import com.we.weblog.service.ContextService;
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


    private ContextService contextService;
    private static int postId = 0;
    private static String tagName = null;

    public FrontController(ContextService blogService){
        this.contextService = blogService;
    }

    @GetMapping("/years_blog_data")
    @ResponseBody
    public List<YearBlog>  getYearBlogs(  HttpServletResponse response) throws IOException {
        int page = 1;  //先默认为1吧
        List<YearBlog> blogList = contextService.getYearBlog(page);

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
        response.sendRedirect("/article.html");

    }

    @GetMapping("/post")
    @ResponseBody
    public Map<String, Context> postData( ){
        Map<String,Context> map  = new HashMap<>();
        Context currentContext = contextService.getBlogById(postId);
        Context preContext = contextService.getPreviousBlog(postId);
        Context nextContext = contextService.getNextBlog(postId);

        map.put("current", currentContext);
        map.put("next", nextContext);
        map.put("previous", preContext);
        return map;
    }

    @GetMapping("/tags_data")
    @ResponseBody
    public List<String> getTags(){
        List<String> list;
        list = contextService.getAllKindTags();
        return list;
    }

    /**
     * 捕获点击的博客类别的get请求
     * @param tag
     * @param response
     * @throws IOException
     */
    @GetMapping("/tags/{tag}")
    public void getTagName(@PathVariable String tag,HttpServletResponse response) throws IOException {
        tagName = tag;
        response.sendRedirect("/tagdetail.html");
    }

    /**
     *  根据tags 展示所有博客
     * @return
     */
    @GetMapping("/tags_detail_data")
    @ResponseBody
    public  List<Context> tagDetailData(){
        List<Context> list;
        list = contextService.getBlogsByTag(tagName);
        return list;
    }




}
