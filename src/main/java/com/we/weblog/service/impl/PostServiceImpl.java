package com.we.weblog.service.impl;

import com.we.weblog.domain.*;
import com.we.weblog.domain.enums.PostStatus;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.domain.modal.YearBlog;
import com.we.weblog.mapper.PostMapper;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import com.we.weblog.util.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {

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
   public int getCategoryCount(){
        return postMapper.findAllCategory().size();
   }

    @Override
    public List<Category> sortBlogsByCategories(){
        List<Post> contexts = postMapper.findPostsByCategory();
        return getBlogsFromTags(contexts);
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
    public List<Category>  getBlogsFromTags(List<Post> contexts){
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
        return  categoryBlogs;
    }

    /**
     * 获取最新博客Id
     *
     * @return
     */
    @Override
    public int getLastestBlogId(){
        return postMapper.findLastestPost().getUid();
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
    public Post findPreviousPost(int uid) {
        Post context =postMapper.findPreviousPost(uid);
        if(context == null){
            return null;
        }
        context.setMonth(TimeUtil.getFormatClearToDay(context.getCreated()));
        return context;
    }

    /**
     * 查询Id之后的文章
     *
     * @return List
     */
    @Override
    public Post findNextPost(int uid) {
        Post context = postMapper.findNextPost(uid);
        if (context == null) {
            return null;
        }
        context.setMonth(TimeUtil.getFormatClearToDay(context.getCreated()));
        return context;
    }

    @Override
    public Post findAuthor() throws Exception {
        Post context = postMapper.findAuthor();
        if (context == null) {
            throw new Exception("关于我没创建");
        }
        context.setMonth(TimeUtil.getFormatClearToDay(context.getCreated()));
        return context;
    }


    @Override
    public List<Post> findPostsByTagName(String tagName) {
        return postMapper.findPostByTagName(tagName);
    }

    /**
     * 根据年份和月份查询文章
     * @param page
     * @return
     */
    @Override
    public List<YearBlog> findPostByYearAndMonth(int page) throws IOException {
        int start = (page - 1) * 12;
        List<Post> list = postMapper.findPostByYearAndMonth(start);
        return sortBlogsByYears(list);
    }

    /**
     * 得到博客总数
     * @return
     */
    @Override
    public int findPostCount() {
        return postMapper.findPostNumber();
    }


    /**
     * 得到页面管理的信息
     * @return
     */
    @Override
    public List<Post> getArticlePages(){
        return sortPostDate(postMapper.findPostByPageType(Types.PAGE));
    }

    @Override
    public int removePostCategory(String name) {
        return postMapper.removePostCategory(name);
    }


    @Override
    public List<Post> findByTagName(String tagName) {
        return postMapper.findByTagName(tagName);
    }

    /**
     * 访问量增加
     *
     * @return
     */
    @Override
    public void updatePostVisit(Post context){
        context.setHits(context.getHits() + 1);
        postMapper.updateOnePostVisit(context);
    }

    /**
     * 新增文章
     *
     * @param post Post
     * @return Post
     */
    @Override
    public void saveByPost(Post post) throws SQLException {
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
    }

    /**
     * 根据编号删除文章
     *
     * @param postId postId
     * @return Post
     */
    @Override
    public Integer removeByPostId(Integer postId) {
        return postMapper.removeByPostId(postId);
    }

    @Override
    public Post findByPostId(int postId) {
        Post context = postMapper.findPostById(postId);
        if(context == null){
            return null;
        }
        context.setMonth(TimeUtil.getFormatClearToDay(context.getCreated()));
        return context;
    }

    /**
     * 查询前五条数据
     *
     * @return List
     */
    @Override
    public List<Post> findLastestPost(int limit) {
        if (limit < 0 || limit >20) {
            limit = 10;
        }
        return  sortPostDate(postMapper.findLastPostsByPage(limit));
    }

    @Override
    public void updatePostStatus(Integer postId, Integer status) {
        postMapper.updateByStatus(postId,status);
    }

    @Override
    public List<Post> findAllPosts(int page) {
        if (page < 0 || page > 10){
            page = 1;
        }
        page = page * 10;
        return sortPostDate(postMapper.findRecent10Posts(page));
    }

    @Override
    public List<Post> findAllPosts(){
        return sortPostDate(postMapper.findAllPosts());

    }

    @Override
    public List<Post> findAllPostsByStatus(int status) {
        return sortPostDate(postMapper.findAllPostsByStatus(status));
    }

    @Override
    public void updatePost(Post context, int uid) throws SQLException {

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