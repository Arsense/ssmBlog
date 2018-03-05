package com.we.weblog.controller.admin;


import com.we.weblog.domain.Context;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.service.ContextService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/admin/page")
public class PageController {


    private ContextService contextService;

    //编辑 删除 添加功能

    @Autowired
    public  PageController(ContextService contextService){
        this.contextService = contextService;

    }



    @PostMapping("/publish")
    @ResponseBody
    public void createPages(Context context, HttpServletResponse response) throws SQLException, IOException {

        context.setType(Types.PAGE);
        context.setTags("test");  //tags not null
        contextService.addBlog(context);

        response.sendRedirect("/admin/pages.html");

    }


    @GetMapping("/modify")
    @ResponseBody
    public void modifyPages(Context context){
            int a = 3;
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public void deletePages(@PathVariable  int id){

        contextService.deleteBlogById(id);
    }








}
