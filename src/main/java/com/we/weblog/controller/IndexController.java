package com.we.weblog.controller;

import com.vue.adminlte4j.model.AppInfo;
import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.support.ModelConfigManager;
import com.we.weblog.data.AppInfoInJvm;
import com.we.weblog.data.MenuApiInJvm;
import com.we.weblog.service.Impl.BlogService;
import com.we.weblog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    private BlogService blogService;

    @Autowired
    public IndexController(BlogService blogService) {
        this.blogService = blogService;
    }
    ;
    @GetMapping("/get_app_info")
    @ResponseBody
    Map getAppInfo() {

        UIModel uiModel = new UIModel()
                .menu(MenuApiInJvm.getMenu())
                .appInfo(AppInfoInJvm.getAppInfo())// 1
                .isLogin(true) ;

        TableData tableData = new TableData() ;
        tableData.configDisplayColumn(TableData.createColumn("title" , "标题") );
        tableData.configDisplayColumn(TableData.createColumn("tags" , "标签" ));
        uiModel.put("tableData" , tableData ) ;
        //return uiModel ;
        return uiModel ;
    }

    @GetMapping("/get_table_data")
    @ResponseBody
    Map<String,Object> get_table_data() {
        UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData() ;
        tableData.configDisplayColumn(TableData.createColumn("blogId" , "博客编号") );
        tableData.configDisplayColumn(TableData.createColumn("title" , "标题") );
        tableData.configDisplayColumn(TableData.createColumn("tags" , "标签" ));
        tableData.configDisplayColumn(TableData.createColumn("date" , "创建日期" ));

        //遍历查询数据库
        List<Blog> tempBlogs = new ArrayList<>();
        tempBlogs = blogService.showBlogs(1);

        for(Blog blog : tempBlogs){
            tableData.addData(blog);
        }

        tableData.setTotalSize(50);
        uiModel.tableData(tableData);
        return uiModel ;
    }

    @GetMapping("/get_app_data")
    @ResponseBody
    AppInfo getAppinfoData() throws IOException {

        return ModelConfigManager.getAppInfo();
    }


    @PostMapping("/update_app_data")
    @ResponseBody
    com.vue.adminlte4j.model.UIModel updateAppinfo(@RequestBody AppInfo appinfo)  {
        try {
            ModelConfigManager.storeAppInfo(appinfo);
            return com.vue.adminlte4j.model.UIModel.success().setMsg("修改成功！") ;
        } catch (IOException e) {
            return com.vue.adminlte4j.model.UIModel.fail().setMsg("修改失败!") ;
        }
    }





}
