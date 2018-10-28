package com.we.weblog.service.impl;

import com.we.weblog.domain.*;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.mapper.PostMapper;
import com.we.weblog.service.PostService;
import com.we.weblog.tool.TimeTool;
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


   public int getCategoryCount(){
       return postMapper.selectAllCategories().size();
   }


    public List<Category> sortBlogsByCategories(){
        List<Post> contexts = postMapper.selectBlogsByCategories();
        return getBlogsFromTags(contexts);
    }


    public List<String> getCategories(){
       return postMapper.selectAllCategories();
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
    public int getLastestBlogId(){
        return postMapper.getblogId().getUid();
    }



    public Post getAboutme() throws Exception {
       Post context = postMapper.selectAboutMe();
       if(context == null){
           throw new Exception("关于我没创建");
       }
       context.setMonth(TimeTool.getFormatClearToDay(context.getCreated()));

        return context;
    }



    public  List<Post> getLastestBlogs(){
        return sortPostDate(postMapper.getTenBlogs(6));
    }



    public void updateBlog(Post context, int uid) throws SQLException {
        try{
            postMapper.updateBlog(context,uid);
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("update fail");
        }
//        updateBlogTag(context.getTags(), uid);
    }


    /**
     *  批量查询博客
     * @param page
     * @return
     */
    public List<Post> showBlogs(int page) {
        if (page < 0 || page > 10){
            page = 1;
        }
        page = page * 10;
        return sortPostDate(postMapper.getTenBlogs(page));
    }



    public List<Post> getBlogsByTag(String tagName) {
        return postMapper.selectBlogByTag(tagName);
    }


    /**
     * 查询Id之前的文章
     *
     * @return List
     */
    @Override
    public Post findPreviousPost(int uid) {

        Post context =postMapper.getPreviousBlog(uid);
        if(context == null){
            return null;
        }
        context.setMonth(TimeTool.getFormatClearToDay(context.getCreated()));

        return context;
    }

    /**
     * 查询Id之后的文章
     *
     * @return List
     */
    @Override
    public Post findNextPost(int uid) {
        Post context = postMapper.getNextBlog(uid);
        if (context == null) {
            return null;
        }
        context.setMonth(TimeTool.getFormatClearToDay(context.getCreated()));

        return context;

    }

    @Override
    public Post findAuthor() throws Exception {
        return null;
    }

    @Override
    public void updatePost(Post context, int uid) throws SQLException {

    }

    @Override
    public List<Post> findPostByPage(int page) {
        return null;
    }

    @Override
    public List<Post> findPostsByTagName(String tagName) {
        return null;
    }

    /**
     * 根据年份和月份查询文章
     * @param page
     * @return
     */
    public List<YearBlog> findPostByYearAndMonth(int page) throws IOException {
        int start = (page - 1) * 12;
        List<Post> list = postMapper.selectBlogsByYear(start);
        return sortBlogsByYears(list);

    }

    @Override
    public int findPostCount() {
        return postMapper.getBlogNumber();
    }

    public List<YearBlog> sortBlogsByYears(List<Post> bloglist) throws IOException{
        List<YearBlog> yearBlogs = new ArrayList<>();

        Map<Integer,YearBlog> yearMap = new HashMap<>();

        for(Post context : bloglist){
            Date date = context.getCreated();
            context.setMonth(TimeTool.getEdate(date));
            int year = TimeTool.getYear(date);
            if(yearMap.containsKey(year)){
                yearMap.get(year).getYearContexts().add(context);
            } else {
                YearBlog yearBlog = new YearBlog(year,new ArrayList<Post>());
                yearMap.put(year,yearBlog);
                yearBlog.getYearContexts().add(context);
                yearBlogs.add(yearBlog);
             }
         }
        return yearBlogs;
    }


    /**
     * 得到页面管理的信息
     * @return
     */
    public List<Post> getArticlePages(){
        return sortPostDate(postMapper.getPagesByType(Types.PAGE));
    }

    @Override
    public int removePostCategory(String name) {
        return 0;
    }

    @Override
    public void updatePostView(Post context) {

    }


    public int deleteCatories(String name){
        return postMapper.deleleCategoryByName(name);
    }

    /**
     * 访问量增加
     * @return
     */
    public void addOneHits(Post context){
        context.setHits(context.getHits() + 1);
        postMapper.updateHits(context);
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
        post.setCreated(new Date(System.currentTimeMillis()));
        postMapper.insertBlog(post);

        try{
//            addBlogTags(post.getTags(), post.getUid());
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("addBlog fail");
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
    public Post updatePostStatus(Long postId, Integer status) {
        return null;
    }

    @Override
    public List<Post> findAllPosts(String postType) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyWord) {
        return null;
    }

    @Override
    public Post findByPostId(int postId) {
        Post context = postMapper.getBlogById(postId);
        if(context == null){
            return null;
        }
        context.setMonth(TimeTool.getFormatClearToDay(context.getCreated()));
        return context;
    }

    /**
     * 查询前五条数据
     *
     * @return List
     */
    @Override
    public List<Post> findLastestPost(int limit) {
        if(limit < 0 || limit >20){
            limit = 10;
        }
        return  sortPostDate(postMapper.getNewBlogs(limit));
    }


    /**
     * 将Date变成年月份
     * @param pages
     * @return
     */
    private  List<Post> sortPostDate(List<Post> pages) {
        for(Post page : pages){
            String time = TimeTool.getFormatClearToDay(page.getCreated());
            page.setMonth(time);
        }
        return  pages;
    }
}