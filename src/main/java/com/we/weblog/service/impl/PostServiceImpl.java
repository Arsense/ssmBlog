package com.we.weblog.service.impl;


import com.we.weblog.domain.*;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.mapper.ContextMapper;
import com.we.weblog.mapper.TagMapper;
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
    private ContextMapper contextMapper;

    @Resource
    private TagMapper tagMapper;



   public int getCategoryCount(){
       return  contextMapper.selectAllCategories().size();
   }


    public List<Category> sortBlogsByCategories(){
        List<Post> contexts = contextMapper.selectBlogsByCategories();
        return getBlogsFromTags(contexts);
    }


    public List<String> getCategories(){
       return contextMapper.selectAllCategories();
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
        return contextMapper.getblogId().getUid();
    }

    /**
     *  将tags拆分放到数组里
     * @param tagString
     * @return
     */
    public List<String> getTagList(String tagString){
        List<String> tagList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(tagString,",");
        while (st.hasMoreTokens()){
            tagList.add(st.nextToken());
        }
        return tagList;
    }


    public Post getAboutme() throws Exception {

       Post context = contextMapper.selectAboutMe();
       if(context == null){
           throw new Exception("关于我没创建");
       }
       context.setMonth(TimeTool.getFormatClearToDay(context.getCreated()));

        return context;
    }

    /**
     * 添加博客标签
     * @param tags
     * @param id
     */
    public  void addBlogTags(String tags,int id){
        List<String> tagList = getTagList(tags);
        for(String tag:tagList){
            tagMapper.insertBlogTag(tag,id);
        }
    }


    public  List<Post> getLastestBlogs(){

        return sortContextDate(contextMapper.getTenBlogs(6));
    }

    /**
     * 更新博客标签 实际是删除重插入
     * @param tags
     * @param id
     */
    public  void updateBlogTag(String tags,int id){
        tagMapper.deleteTagById(id);
        List<String> tagList = getTagList(tags);
        for(String tag:tagList){
            tagMapper.insertBlogTag(tag,id);
        }

    }




    public void updateBlog(Post context, int uid) throws SQLException {
        try{
            contextMapper.updateBlog(context,uid);
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("update fail");
        }
        updateBlogTag(context.getTags(), uid);
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
        return sortContextDate(contextMapper.getTenBlogs(page));
    }



    public List<Post> getBlogsByTag(String tagName) {
        return contextMapper.selectBlogByTag(tagName);
    }


    public Post getPreviousBlog(int uid) {


        Post context =contextMapper.getPreviousBlog(uid);
        if(context == null){
            return null;
        }
        context.setMonth(TimeTool.getFormatClearToDay(context.getCreated()));

        return context;
    }


    public Post getNextBlog(int uid) {
        Post context = contextMapper.getNextBlog(uid);
        if (context == null) {
            return null;
        }
        context.setMonth(TimeTool.getFormatClearToDay(context.getCreated()));

        return context;

    }

    /**
     * 根据页码显示每个年份的博客
     * @param page
     * @return
     */
    public List<YearBlog> getYearBlog(int page) throws IOException {
        int start = (page - 1) * 12;
        List<Post> list = contextMapper.selectBlogsByYear(start);
        return sortBlogsByYears(list);

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


    public int getTotalBlog() {
        return contextMapper.getBlogNumber();
    }

    public List<String> getAllKindTags(){
        return tagMapper.selectAllKindTags();
    }


    public List<Post> getRecentBlogs(int limit){
        if(limit < 0 || limit >20){
            limit = 10;
        }
        return  sortContextDate(contextMapper.getNewBlogs(limit));

    }
    /**
     * 得到页面管理的信息
     * @return
     */
    public  List<Post> getArticlePages(){
        return sortContextDate(contextMapper.getPagesByType(Types.PAGE));
    }

    /**
     * 将Date变成年月份
     * @param pages
     * @return
     */
    public  List<Post> sortContextDate(List<Post> pages){
        for(Post page:pages){
            String time = TimeTool.getFormatClearToDay(page.getCreated());
            page.setMonth(time);
        }
        return  pages;
    }

    public int deleteCatories(String name){
        return contextMapper.deleleCategoryByName(name);
    }

    /**
     * 访问量增加
     * @return
     */
    public void addOneHits(Post context){
        context.setHits(context.getHits()+1);
        contextMapper.updateHits(context);
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
        contextMapper.insertBlog(post);

        try{
            addBlogTags(post.getTags(), post.getUid());
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
        return contextMapper.removeByPostId(postId);
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
        Post context = contextMapper.getBlogById(postId);
        if(context == null){
            return null;
        }
        context.setMonth(TimeTool.getFormatClearToDay(context.getCreated()));
        return context;
    }

    @Override
    public List<Post> findLastestPost() {
        return null;
    }
}