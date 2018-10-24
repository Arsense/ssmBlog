package com.we.weblog.web.controller;

import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.domain.Category;
import com.we.weblog.domain.Comment;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.YearBlog;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.service.CommentService;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     拦截器，资源路径配置
 * </pre>
 * 首页、归档、分类、评论
 *   前端页面显示的控制器
 */
@Controller
public class IndexController extends  BaseController {


    private PostService postService;
    private CommentService commentSerivce;
    private static int  postId ;
    private static String  tagName = null;
    private TagService tagService;

    @Autowired
    public IndexController(PostService postService, TagService tagService, CommentService commentSerivce){
        this.commentSerivce = commentSerivce;
        this.tagService = tagService;
        this.postService = postService;
        //初始化postID 为第一个 防止单独访问为0 什么都木有
        postId = postService.getLastestBlogId();
    }

    /**
     * 首页视图
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /**
     * 添加评论
     * @param
     * @return
     */
    @PostMapping("/comments/send")
    @ResponseBody
    public UIModel addComment(@RequestBody Comment comment ){
        if (StringUtils.isEmpty(comment) || comment.getArticle_id() <= 0 ) {
            return UIModel.fail().msg("评论失败,输入信息有误");
        } else if (!comment.getEmail().contains("@")) {
            return UIModel.fail().msg("邮箱格式不正确");
        } else if (comment.getContent().length() < 5) {
            return UIModel.fail().msg("您的评论太短");
        } else if (comment.getContent() == null) {
            return UIModel.fail().msg("您的评论不能为空");
        }
        //处理XSS
        comment.setContent(cleanXSS(comment.getContent()));
        int result = commentSerivce.saveComment(comment,request);
        if(result > 0)
            return UIModel.success().msg("评论成功");
        else
            return UIModel.fail().msg("评论失败,输入内容有误");
    }

    /**
     * 标签显示 删除吧 只留分类吧
     */
    @GetMapping("/get_kind_blogs")
    @ResponseBody
    public List<Category> getBlogsByTag(){
        List<Category> lists = postService.sortBlogsByCategories();
        return lists;
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

    @GetMapping("/tags_data")
    @ResponseBody
    public List<String> getAllTags(){
        List<String> list = postService.getAllKindTags();
        return list;
    }

    /**
     * 捕获点击的博客类别的get请求
     * @param tag
     * @param response
     * @throws IOException
     */
    @GetMapping("/tags/{tag}")
    public void getTagName(@PathVariable String tag,
                           HttpServletResponse response) throws IOException {
        tagName = tag;
        response.sendRedirect("/tagdetail.html");
    }

    /**
     *  根据tags 展示所有博客
     * @return
     */
    @GetMapping("/tags_detail_data")
    @ResponseBody
    public  List<Post> tagDetailData() {
        return  postService.getBlogsByTag(tagName);
    }


    /**
     *  获取标签信息
     * @return
     */
    @GetMapping("/get_pages_data")
    @ResponseBody
    UIModel getTags() {
        FormModel formModel = new FormModel();
        formModel.createFormItem("title").setHidden(false).setLabel("页面名称");
        formModel.createFormItem("slug").setHidden(false).setLabel("页面路径");
        formModel.createFormItem("month").setHidden(false).setLabel("发布时间");
        formModel.createFormItem("publish").setHidden(false).setLabel("发布状态");

        List<Post> tempContexts = postService.getArticlePages();

        TableData tableData = new TableData() ;
        tableData.setTotalSize(10);
        tableData.setDataItems(tempContexts);
        tableData.setFormItems(formModel.getFormItems());

        return UIModel.success().tableData(tableData) ;
    }


    @GetMapping("/get_all_datas/{page}")
    @ResponseBody
    public Map<String,Object> getIndexData(@PathVariable String page) throws Exception {
        //如果是首页
        Map<String,Object> maps = new HashMap<>();
        List<String> tagsName = tagService.getTotalTagsName();
        int blogCount = postService.getTotalBlog();
        int categoryCount = postService.getCategoryCount();
        int totalTags = 10;
        //旁边博客展示都需要
        List<Post> blogs = postService.getLastestBlogs();
        maps.put(Types.BLOGS,blogs);

        sortPagesMap(maps,page);
        maps.put(Types.TAG_NAME,tagsName);
        maps.put(Types.TAG_COUNT,totalTags);
        maps.put(Types.BLOG_COUNT,blogCount);
        maps.put(Types.CATEGORY_COUNT,categoryCount);

        return maps;
    }

    /**
     * todo 根据标签名显示分类
     * @param tagName
     */
    @GetMapping("/tags/{name}")
    public void getTagDetail(@PathVariable String tagName){

    }


    /**
     * 前端数据处理的主要函数
     * @param maps
     * @param pageType
     * @throws Exception
     */
    public  void sortPagesMap(Map<String,Object> maps, String pageType) throws Exception {
        if (pageType.equals(Types.PAGE_CATEGORY)) {
            List<Category> cBlogs = postService.sortBlogsByCategories();
            maps.put(Types.CATEGORIES,cBlogs);
        } else if (pageType.contains(Types.PAGE_ARTICLE)) {

            int getId = Integer.parseInt(pageType.substring(7,pageType.length()));
            if(getId <= 0){
                throw new Exception("GET ARTICLE ID FAIL,CHECK");
            }
            Post currentContext = postService.findByPostId(getId);
            //增加一次访问量
            postService.addOneHits(currentContext);
            Post preContext = postService.getPreviousBlog(getId);
            Post nextContext = postService.getNextBlog(getId);
            int uid = currentContext.getUid();
            //显示评论
            if (uid > 0) {
                List<Comment> comments =  commentSerivce.findCommentByUid(uid);
                maps.put(Types.COMMENTS,comments);
            }
            maps.put(Types.CURRENT_BLOG, currentContext);
            maps.put(Types.NEXT_BLOG, nextContext);
            maps.put(Types.PREVIOUS_BLOG, preContext);

        } else if (pageType.equals(Types.PAGE_ARCHIVE)) {

            List<YearBlog> yearBlogs = postService.getYearBlog(1);
            maps.put(Types.BLOGS_DATA,yearBlogs);

        } else if (pageType.equals(Types.PAGE_ABOUT)) {

            Post about = postService.getAboutme();
            maps.put(Types.BLOG,about);

        }
    }




}
