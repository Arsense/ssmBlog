package com.we.weblog.controller.admin;


import com.we.weblog.service.ContextService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/page")
public class PageController {


    private ContextService contextService;

    //编辑 删除 添加功能

    @Autowired
    public  PageController(ContextService contextService){
        this.contextService = contextService;

    }



    @GetMapping("/get")
    @ResponseBody
    public void getPageArticle(){
      //  List<Context> contexts = blogService.getBlogsPages();


    }


    @GetMapping("/edit")
    public void editPage(@Param("id")int id){


    }

    @GetMapping("/add")
    public void addPage(@Param("id")int id){


    }


    @GetMapping("/delete")
    public void deletePage(@Param("id")int id){


    }






}
