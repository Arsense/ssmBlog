package com.we.weblog.web.controller.admin;


import com.we.weblog.service.LogsService;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import com.we.weblog.web.controller.BaseController;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.tool.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/page")
public class PageController extends BaseController{


    private PostService postService;
    private TagService tagService;
    private LogsService logService;

    //编辑 删除 添加功能

    @Autowired
    public  PageController(LogsService logService,PostService postService,TagService tagService) {
        this.logService = logService;
        this.postService = postService;
        this.tagService = tagService;
    }


    @PostMapping("/publish")
    @ResponseBody
    public void createPages(Post context, HttpServletResponse response) throws Exception {
        context.setType(Types.PAGE);
        context.setTags("test");  //tags not null
        postService.saveByPost(context);

        Log loginLog =new Log(LogActions.ADD_PAGES,"admin", IpTool.getIpAddress(request),1);
        if (logService.saveByLogs(loginLog)<0) {
            throw new Exception("添加新页面失败");
        }
        response.sendRedirect("/admin/pages.html");
    }



    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deletePages(@PathVariable  int id){
        postService.removeByPostId(id);
        tagService.deleteTag(id);
    }








}
