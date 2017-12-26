package com.we.weblog.controller;


import com.we.weblog.data.AppInfoInJvm;
import com.we.weblog.data.MenuApiInJvm;
import com.we.weblog.service.BlogService;
import com.we.weblog.TableData;
import com.we.weblog.UIModel;
import com.we.weblog.domain.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    private BlogService blogService;

    //遍历查询数据库
    public static List<Blog> tempBlogs = new ArrayList<>();

    public IndexController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping("/get_app_info")
    @ResponseBody
    Map getAppInfo() {

        UIModel uiModel = new UIModel()
                .menu(MenuApiInJvm.getMenu())
                .appInfo(AppInfoInJvm.getAppInfo())
                .isLogin(true);

        TableData tableData = new TableData() ;
        tableData.configDisplayColumn(TableData.createColumn("title" , "标题") );
        tableData.configDisplayColumn(TableData.createColumn("tags" , "标签" ));
        Map<String,Object> map = new HashMap<>() ;
        map.put("name" , "xiaohong ") ;
        map.put("age" , "1") ;
        tableData.addData(map);
        uiModel.put("tableData" , tableData ) ;
        //return uiModel ;
        return uiModel ;
    }

    @GetMapping("/get_table_data")
    @ResponseBody
    Map<String,Object> get_table_data(@RequestParam(required = false) String name) {
        UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData() ;
//        tableData.configDisplayColumn(TableData.createColumn("id" , "博客编号") );
        tableData.configDisplayColumn(TableData.createColumn("title" , "标题") );
        tableData.configDisplayColumn(TableData.createColumn("tags" , "标签" ));
        tableData.configDisplayColumn(TableData.createColumn("date" , "创建日期" ));


        for(Blog blog : tempBlogs){
            tableData.addData(blog);
        }


        if("".equals(name)) {
            tableData.setTotalSize(0);
            tableData.setPage(false);
        }

        tableData.setTotalSize(50);
        uiModel.tableData(tableData);
        return uiModel ;
    }



}
