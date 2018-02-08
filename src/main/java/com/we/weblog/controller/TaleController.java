package com.we.weblog.controller;


import com.we.weblog.domain.Context;
import com.we.weblog.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TaleController {

    private ContextService contextService;


    @Autowired
    public TaleController(ContextService contextService){
        this.contextService = contextService;
     }


    @GetMapping("/get_lasted_blog")
    @ResponseBody
    List<Context> getRecentBlogs(){
        List<Context> lists = contextService.getRecentBlogs(5);
        return lists;
    }
}
