package com.we.weblog.controller.admin;


import com.we.weblog.domain.Context;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.service.ContextService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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



    @GetMapping("/publish")
    @ResponseBody
    public void createPages(Context context) throws SQLException {

        context.setPublish(Types.PAGE);
        contextService.addBlog(context);

    }


    @GetMapping("/modify")
    @ResponseBody
    public void modifyPages(Context context){
            int a = 3;



    }


    @GetMapping("/delete")
    @ResponseBody
    public void deletePages(int id){

        contextService.deleteBlogById(id);
    }








}
