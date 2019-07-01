package com.we.weblog.service.impl;

import com.we.weblog.domain.Category;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.result.Result;
import com.we.weblog.domain.enums.PostStatus;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.domain.modal.YearBlog;
import com.we.weblog.domain.util.TimeUtil;
import com.we.weblog.mapper.PostMapper;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    //todo 查询全合并成一个 改为传javaBean的
    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

    @Resource
    private PostMapper postMapper;
    @Resource
    private TagService tagService;

    /**
     * 得到分类总数
     *
     * @return
     */
    @Override
   public Result getCategoryCount(){
        Result result = new Result();
        postMapper.findAllCategory().size();
        return result;
   }

    @Override
    public Result sortBlogsByCategories(){
        Result result = new Result();
        List<Post> contexts = postMapper.findPostsByCategory();
        getBlogsFromTags(contexts);
        return result;
    }

    /**
     * 得到所有分类名
     *
     * @return
     */
    public List<String> getCategories(){
        return postMapper.findAllCategory();
    }

    /**
     * 根据分类分配到相应的类中
     * @return
     */
    public Result  getBlogsFromTags(List<Post> contexts){

        Result result = new Result();

        List<Category> categoryBlogs = new ArrayList<>();
        Map<String, Category>  maps = new HashMap<>();
        for(Post context:contexts){
            if(maps.containsKey(context.getCategories())){
                //如果已有该标签
                maps.get(context.getCategories()).getBlogs().add(context);
            }else{
                Category cBlog = new Category(context.getCategories(),new ArrayList<Post>());
                maps.put(context.getCategories(),cBlog);
                cBlog.getBlogs().add(context);
                categoryBlogs.add(cBlog);
            }
        }
        return  result;
    }

    /**
     * 获取最新博客Id
     *
     * @return
     */
    @Override
    public Result getLastestBlogId(){

        Result result = new Result();
        postMapper.findLastestPost().getUid();
        return result;
    }


    public  List<Post> getLastestBlogs(){
        return sortPostDate(postMapper.findRecent10Posts(6));
    }

    public void updateBlog(Post context, int uid) throws SQLException {
        try{
            postMapper.updatePostByUid(context,uid);
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("update fail");
        }
        tagService.updateBlogTag(context.getTags(), uid);
    }

    /**
     * 查询Id之前的文章
     *
     * @return List
     */
    @Override
    public Result findPreviousPost(int uid) {

        Result result = new Result();

        Post context =postMapper.findPreviousPost(uid);
        if(context == null){
            return null;
        }
        context.setMonth(TimeUtil.getFormatClearToDay(context.getCreated()));
        return result;
    }

    /**
     * 查询Id之后的文章
     *
     * @return List
     */
    @Override
    public Result findNextPost(int uid) {
        Result result = new Result();

        Post context = postMapper.findNextPost(uid);
        if (context == null) {
            return null;
        }
        context.setMonth(TimeUtil.getFormatClearToDay(context.getCreated()));
        return result;
    }

    @Override
    public Result findAuthor() throws Exception {

        Result result = new Result();

        Post context = postMapper.findAuthor();
        if (context == null) {
            throw new Exception("关于我没创建");
        }
        context.setMonth(TimeUtil.getFormatClearToDay(context.getCreated()));
        return result;
    }


    @Override
    public Result findPostsByTagName(String tagName) {
        Result result = new Result();
        postMapper.findPostByTagName(tagName);
        return result;
    }

    /**
     * 根据年份和月份查询文章
     * @param page
     * @return
     */
    @Override
    public Result findPostByYearAndMonth(int page) throws IOException {
        Result result = new Result();

        int start = (page - 1) * 12;
        List<Post> list = postMapper.findPostByYearAndMonth(start);
        sortBlogsByYears(list);
        return result;
    }

    /**
     * 得到博客总数
     * @return
     */
    @Override
    public Result findPostCount() {
        Result result = new Result();
        postMapper.findPostNumber();
        return result;
    }


    /**
     * 得到页面管理的信息
     * @return
     */
    @Override
    public Result getArticlePages(){

        Result result = new Result();
        sortPostDate(postMapper.findPostByPageType(Types.PAGE));
        return result;
    }

    @Override
    public Result removePostCategory(String name) {

        Result result = new Result();
        postMapper.removePostCategory(name);
        return result;
    }


    @Override
    public Result findByTagName(String tagName) {
        Result result = new Result(false);
        if (StringUtils.isEmpty(tagName)) {
            result.setErrMsg("参数错误");
            return result;
        }
        List<Post> posts = postMapper.findByTagName(tagName);
        if (!CollectionUtils.isEmpty(posts)) {
            result.setData(posts);
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 访问量增加
     *
     * @return
     */
    @Override
    public Result updatePostVisit(Post context){
        Result result = new Result();

        context.setHits(context.getHits() + 1);
        postMapper.updateOnePostVisit(context);
        return result;
    }

    /**
     * 新增文章
     *
     * @param post Post
     * @return Post
     */
    @Override
    public Result saveByPost(Post post) throws SQLException {

        Result result = new Result();

        //默认没有分类则创建分类
        if(StringUtils.isEmpty(post.getCategories())){
            post.setCategories("默认分类");
        }
        //初始化访问量是0
        post.setHits(0);
        post.setPublish(Types.PUBLISH);
        post.setStatus(PostStatus.PUBLISHED.getCode());
        post.setCreated(new Date(System.currentTimeMillis()));
        postMapper.savePost(post);
        try {
            tagService.addBlogTags(post.getTags(), post.getUid());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("添加博客失败");
        }
        return result;
    }

    /**
     * 根据编号删除文章
     *
     * @param postId postId
     * @return Post
     */
    @Override
    public Result removeByPostId(Integer postId) {
        Result result = new Result();
        postMapper.removeByPostId(postId);
        return result;
    }

    @Override
    public Result findByPostId(int postId) {
        Result result = new Result();

        Post context = postMapper.findPostById(postId);
        if(context == null){
            return null;
        }
        context.setMonth(TimeUtil.getFormatClearToDay(context.getCreated()));
        return result;
    }

    /**
     * 查询前五条数据
     *
     * @return List
     */
    @Override
    public Result findLastestPost(int limit) {
        Result result = new Result();

        if (limit < 0 || limit >20) {
            limit = 10;
        }
        sortPostDate(postMapper.findLastPostsByPage(limit));
        return result ;
    }

    @Override
    public Result updatePostStatus(Integer postId, Integer status) {
        postMapper.updateByStatus(postId,status);
        return null;
    }

    @Override
    public Result findAllPosts(int page) {
        Result result = new Result();

        if (page < 0 || page > 10){
            page = 1;
        }
        page = page * 10;
        sortPostDate(postMapper.findRecent10Posts(page));
        return result;
    }

    @Override
    public Result findAllPosts() {
        Result result = new Result();
        sortPostDate(postMapper.findAllPosts());
        return result;

    }

    @Override
    public Result findAllPostsByStatus(int status) {
        Result result = new Result();
        sortPostDate(postMapper.findAllPostsByStatus(status));
        return result;
    }

    @Override
    public Result updatePost(Post context, int uid) throws SQLException {
        return null;
    }


    /**
     * 将Date变成年月份
     * @param pages
     * @return
     */
    private  List<Post> sortPostDate(List<Post> pages) {
        for(Post page: pages){
            page.setMonth(TimeUtil.getFormatClearToDay(page.getCreated()));
        }
        return pages;
    }

    private List<YearBlog> sortBlogsByYears(List<Post> bloglist) {
        List<YearBlog> yearBlogs = new ArrayList<>();
        Map<Integer,YearBlog> yearMap = new HashMap<>();

        for (Post context : bloglist) {
            context.setMonth(TimeUtil.getEdate(context.getCreated()));
            Integer year = TimeUtil.getYear(context.getCreated());
            if (yearMap.containsKey(year)) {
                yearMap.get(year).getYearContexts().add(context);
            } else {
                YearBlog yearBlog = new YearBlog(year, new ArrayList<>());
                yearMap.put(year,yearBlog);
                yearBlog.getYearContexts().add(context);
                yearBlogs.add(yearBlog);
            }
        }
        return yearBlogs;
    }
}