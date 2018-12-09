package com.we.weblog.web.controller.admin;


import com.we.weblog.service.LogsService;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import com.we.weblog.web.controller.core.BaseController;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.domain.util.AddressUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/page")
public class PageAdminController extends BaseController{


    @Resource
    private PostService postService;
    @Resource
    private TagService tagService;
    @Resource
    private LogsService logService;


    @PostMapping("/publish")
    @ResponseBody
    public void createPages(Post context, HttpServletResponse response) throws Exception {
        context.setType(Types.PAGE);
        context.setTags("test");  //tags not null
        postService.saveByPost(context);

        Log loginLog =new Log(LogActions.ADD_PAGES,"admin", AddressUtil.getIpAddress(request),1);
        if (logService.saveByLogs(loginLog)<0) {
            throw new Exception("添加新页面失败");
        }
        response.sendRedirect("/admin/admin_pages.html");
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deletePages(@PathVariable  int id){
        postService.removeByPostId(id);
        tagService.deleteTag(id);
    }








}
