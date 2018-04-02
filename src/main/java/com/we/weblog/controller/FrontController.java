package com.we.weblog.controller;


import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.domain.CategoriesBlog;
import com.we.weblog.domain.Comment;
import com.we.weblog.domain.Context;
import com.we.weblog.domain.YearBlog;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.service.CommentSerivce;
import com.we.weblog.service.ContextService;
import com.we.weblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *   前端页面显示的控制器
 */
@Controller
public class FrontController {


    private ContextService contextService;
    private CommentSerivce commentSerivce;
    private static int postId ;
    private static String tagName = null;
    private TagService tagService;


    @Autowired
    public FrontController(ContextService blogService,TagService tagService,CommentSerivce commentSerivce){
        this.commentSerivce =commentSerivce;
        this.tagService = tagService;
        this.contextService = blogService;
        //初始化postID 为第一个 防止单独访问为0 什么都木有
        postId = contextService.getLastestBlogId();
    }







    /**
     * 标签显示 删除吧 只留分类吧
     */
    @GetMapping("/get_kind_blogs")
    @ResponseBody
    public List<CategoriesBlog> getBlogsByTag(){


        List<CategoriesBlog> lists = contextService.sortBlogsByCategories();

        return lists;

    }





    @GetMapping("/tags/{name}")
    public void getTagDetail(@PathVariable String tagName){



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
     *  获取标签信息
     * @return
     */
    @GetMapping("/get_pages_data")
    @ResponseBody
    UIModel getTagssdata() {
        UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData() ;
        tableData.configDisplayColumn(TableData.createColumn("title" , "页面名称") );
        tableData.configDisplayColumn(TableData.createColumn("slug" , "页面路径") );

        tableData.configDisplayColumn(TableData.createColumn("month" , "发布时间" ));
        tableData.configDisplayColumn(TableData.createColumn("publish" , "发布状态" ));


        //遍历查询数据库
        List<Context> tempContexts=contextService.getArticlePages();

        for(Context context : tempContexts){
            tableData.addData(context);
        }


        //遍历查询数据库
        tableData.setTotalSize(10);
        uiModel.tableData(tableData);
        return uiModel ;
    }








    @GetMapping("/get_all_datas/{page}")
    @ResponseBody
    public Map<String,Object> getFrontData(@PathVariable String page) throws Exception {
        //如果是首页

        Map<String,Object> maps = new HashMap<>();

        List<String> tagsName = tagService.getTotalTagsName();

        int blogCount = contextService.getTotalBlog();
        int totalTags = 10;
        //旁边博客展示都需要
        List<Context> blogs = contextService.getLastestBlogs();
        maps.put(Types.BLOGS,blogs);

        sortPagesMap(maps,page);


        maps.put(Types.TAG_NAME,tagsName);
        maps.put(Types.TAG_COUNT,totalTags);
        maps.put(Types.BLOG_COUNT,blogCount);


        return maps;

    }

    /**
     * 前端数据处理的主要函数
     * @param maps
     * @param pageType
     * @throws Exception
     */
    public  void sortPagesMap(Map<String,Object> maps, String pageType) throws Exception {

        if(pageType.equals(Types.PAGE_CATEGORY)){

            List<CategoriesBlog> cBlogs = contextService.sortBlogsByCategories();               maps.put(Types.CATEGORIES,cBlogs);

        }else if(pageType.equals(Types.PAGE_ARTICLE)){

            Context currentContext = contextService.getBlogById(postId);
            Context preContext = contextService.getPreviousBlog(postId);
            Context nextContext = contextService.getNextBlog(postId);
            int uid = currentContext.getUid();

            //显示评论
            if(uid >0){
                Comment comment =  commentSerivce.getCommentByArticleId(uid);
                maps.put(Types.COMMENT,comment);
            }


            maps.put(Types.CURRENT_BLOG, currentContext);
            maps.put(Types.NEXT_BLOG, nextContext);
            maps.put(Types.PREVIOUS_BLOG, preContext);

        }else if(pageType.equals(Types.PAGE_ARCHIVE)){

            List<YearBlog> yearBlogs = contextService.getYearBlog(1);
            maps.put(Types.BLOGS_DATA,yearBlogs);

        }else if(pageType.equals(Types.PAGE_ABOUT)){

            Context about = contextService.getAboutme();
            maps.put(Types.BLOG,about);
        }
    }



}
