package com.we.weblog.service;


import com.we.weblog.domain.Context;
import com.we.weblog.domain.YearBlog;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.mapping.ContextMapper;
import com.we.weblog.mapping.TagMapper;
import com.we.weblog.tool.TimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.TypeSignature;

import java.io.IOException;
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


    /**
     * 更新博客标签 实际是删除重插入
     * @param tags
     * @param id
     */
    public  void updateBlogTag(String tags,int id){
        tagMapper.deleteTagsById(id);
        List<String> tagList = getTagList(tags);
        for(String tag:tagList){
            tagMapper.insertBlogTag(tag,id);
        }

    }


    /**
     * 添加博客 同时把tags添加进去
     * @param context
     */
    public void addBlog(Context context) {
        context.setCreated(new Date(System.currentTimeMillis()));
        contextMapper.insertBlog(context);
        addBlogTags(context.getTags(), context.getUid());

    }


    public void updateBlog(Context context) {
        contextMapper.updateBlog(context);
        updateBlogTag(context.getTags(), context.getUid());
        updateBlogTag(context.getTags(), context.getUid());
    }

    public void deleteBlogById(int id) {
        int result = contextMapper.deleteBlogById(id);
        if(result == 0){

        }else{

        }
    }


    /**
     *  批量查询博客
     * @param page
     * @return
     */
    public List<Context> showBlogs(int page) {
        if(page <0 || page >10)
            page=1;
        page = page*10;
        return contextMapper.getTenBlogs(page);
    }




    public List<Context> getBlogsByTag(String tagName) {
        return contextMapper.selectBlogByTag(tagName);
    }



    public Context getPreviousBlog(int blogId) {
        return contextMapper.getPreviousBlog(blogId);
    }


    public Context getNextBlog(int blogId) { return contextMapper.getNextBlog(blogId);
    }


    public Context getBlogById(int id) {
        return contextMapper.getBlogById(id);
    }


    /**
     * 根据页码显示每个年份的博客
     * @param page
     * @return
     */
    public List<YearBlog> getYearBlog(int page) throws IOException {
        int start = (page-1)*12;
        List<Context> list = contextMapper.selectBlogsByYear(start);
        return sortBlogsByYears(list);
    }


    public List<YearBlog> sortBlogsByYears(List<Context> bloglist) throws IOException{
        List<YearBlog> yearBlogs = new ArrayList<>();
        Map<Integer,YearBlog> yearMap = new HashMap<>();
        for(Context context : bloglist){
            Date date= context.getCreated();
            context.setMonth(TimeTool.getEdate(date));
            int year = TimeTool.getYear(date);
            if(yearMap.containsKey(year)){
                yearMap.get(year).getYearContexts().add(context);
            }else{
                YearBlog yearBlog = new YearBlog(year,new ArrayList<Context>());
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
        return tagMapper.selectTagkinds();
    }



    public List<Context> getRecentBlogs(int limit){
        if(limit < 0 || limit >20){
            limit = 10;
        }
        return  contextMapper.getNewBlogs(limit);

    }

    /**
     * 得到页面管理的信息
     * @return
     */
    public  List<Context> getArticlePages(){
        return contextMapper.getPagesByType(Types.PAGE);
    }

}