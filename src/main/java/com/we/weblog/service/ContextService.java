package com.we.weblog.service;


import com.we.weblog.domain.*;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.mapper.ContextMapper;
import com.we.weblog.mapper.TagMapper;
import com.we.weblog.tool.TimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


@Service
public class ContextService {
    private ContextMapper contextMapper;
    private TagMapper tagMapper;

    /**
     * 构造函数
     * @param contextMapper
     */
    @Autowired
    public ContextService(ContextMapper contextMapper, TagMapper tagMapper) {
        this.tagMapper = tagMapper;
        this.contextMapper = contextMapper;
}


    /**
     * 得到博客数量
     * @return
     */
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
    public List<Category>    getBlogsFromTags(List<Post> contexts){
        List<Category> categoryBlogs = new ArrayList<>();
        Map<String,Category>  maps = new HashMap<>();

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


    /**
     * 添加博客 同时把tags添加进去
     * @param context
     */
    public void addBlog(Post context) throws SQLException {
        //默认没有分类则创建分类
        if(StringUtils.isEmpty(context.getCategories())){
            context.setCategories("默认分类");
        }
        //初始化访问量是0
        context.setHits(0);
        context.setPublish(Types.PUBLISH);
        context.setCreated(new Date(System.currentTimeMillis()));
        contextMapper.insertBlog(context);

        try{
            addBlogTags(context.getTags(), context.getUid());

        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("addBlog fail");
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

    public void deleteBlogById(int id) {
        int result = contextMapper.deleteBlogById(id);
    }

    /**
     *  批量查询博客
     * @param page
     * @return
     */
    public List<Post> showBlogs(int page) {
        if(page <0 || page >10)
            page=1;
        page = page*10;
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


        Post context =contextMapper.getNextBlog(uid);
        if(context == null){
            return null;
        }
        context.setMonth(TimeTool.getFormatClearToDay(context.getCreated()));

        return context;

    }


    public Post getBlogById(int id) {

        Post context =contextMapper.getBlogById(id);
        if(context == null){
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

        int start = (page-1)*12;
        List<Post> list = contextMapper.selectBlogsByYear(start);
        return sortBlogsByYears(list);

    }





    public List<YearBlog> sortBlogsByYears(List<Post> bloglist) throws IOException{
        List<YearBlog> yearBlogs = new ArrayList<>();
        Map<Integer,YearBlog> yearMap = new HashMap<>();
        for(Post context : bloglist){
            Date date= context.getCreated();
            context.setMonth(TimeTool.getEdate(date));
            int year = TimeTool.getYear(date);
            if(yearMap.containsKey(year)){
                yearMap.get(year).getYearContexts().add(context);
            }else{
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

}