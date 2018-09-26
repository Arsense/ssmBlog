package com.we.weblog.controller.admin;


import com.we.weblog.controller.BaseController;
import com.we.weblog.domain.Context;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.service.ContextService;
import com.we.weblog.service.LogService;
import com.we.weblog.service.TagService;
import com.we.weblog.tool.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/page")
public class PageController extends BaseController{


    private ContextService contextService;
    private TagService  tagService;
    private LogService logService;

    //编辑 删除 添加功能

    @Autowired
    public  PageController(LogService logService,ContextService contextService,TagService tagService){
        this.logService = logService;
        this.contextService = contextService;
        this.tagService = tagService;
    }


    @PostMapping("/publish")
    @ResponseBody
    public void createPages(Context context, HttpServletResponse response) throws Exception {
        context.setType(Types.PAGE);
        context.setTags("test");  //tags not null
        contextService.addBlog(context);

        Log loginLog =new Log(LogActions.ADD_PAGES,"admin", IpTool.getIpAddress(request),1);
        if (logService.addLog(loginLog)<0) {
            throw new Exception("添加新页面失败");
        }
        response.sendRedirect("/admin/pages.html");
    }



    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deletePages(@PathVariable  int id){
        contextService.deleteBlogById(id);
        tagService.deleteTag(id);
    }








}
