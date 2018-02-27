package com.we.weblog.controller;


import com.vue.adminlte4j.model.AppInfo;
import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.support.ModelConfigManager;
import com.we.weblog.data.MenuApiInJvm;
import com.we.weblog.domain.Context;
import com.we.weblog.domain.YearBlog;
import com.we.weblog.service.ContextService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
    public List<YearBlog>  getYearBlogs() throws IOException {

        int page = 1;  //先默认为1吧
        return contextService.getYearBlog(page);

    }

    /**
     *  先处理好数据 要不然后让所有url 在
     * @param id
     * @return
     */
    @GetMapping("/post/{id}")
    public void post(@PathVariable String id,HttpServletResponse response ) throws IOException {

        postId = Integer.parseInt(id);
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


    @GetMapping("/admin/get_table_data")
    @ResponseBody
    Map<String,Object> get_table_data() {

            UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData() ;

        tableData.configDisplayColumn(TableData.createColumn("uid" , "博客编号") );
        tableData.configDisplayColumn(TableData.createColumn("title" , "标题") );
        tableData.configDisplayColumn(TableData.createColumn("tags" , "标签" ));
        tableData.configDisplayColumn(TableData.createColumn("month" , "创建日期" ));

        //遍历查询数据库
        List<Context> tempContexts=contextService.showBlogs(1);;

        for(Context context : tempContexts){
            tableData.addData(context);
        }

        tableData.setTotalSize(contextService.getTotalBlog());
        uiModel.tableData(tableData);
        return uiModel ;
    }

    /**
     * 前端 评论信息
     * @return
     */
    @GetMapping("/admin/get_comment_data")
    @ResponseBody
    Map<String,Object> getCommentsdata() {

        UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData() ;

        tableData.configDisplayColumn(TableData.createColumn("content" , "评论内容") );
        tableData.configDisplayColumn(TableData.createColumn("author" , "评论人") );
        tableData.configDisplayColumn(TableData.createColumn("created" , "评论时间" ));
        tableData.configDisplayColumn(TableData.createColumn("mail" , "评论人邮箱" ));
        tableData.configDisplayColumn(TableData.createColumn("status" , "评论状态" ));

        //遍历查询数据库
        tableData.setTotalSize(10);
        uiModel.tableData(tableData);
        return uiModel ;
    }


    /**
     *  获取标签信息
     * @return
     */
    @GetMapping("/get_tags_data")
    @ResponseBody
    UIModel getTagssdata() {
        UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData() ;
        tableData.configDisplayColumn(TableData.createColumn("title" , "页面名称") );
        tableData.configDisplayColumn(TableData.createColumn("slug" , "页面路径") );

        tableData.configDisplayColumn(TableData.createColumn("month" , "发布时间" ));
        tableData.configDisplayColumn(TableData.createColumn("publish" , "发布状态" ));


        //遍历查询数据库
        List<Context> tempContexts=contextService.getArticlePages();;

        for(Context context : tempContexts){
            tableData.addData(context);
        }


        //遍历查询数据库
        tableData.setTotalSize(10);
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
