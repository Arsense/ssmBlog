package com.we.weblog.service.Impl;


import com.we.weblog.mapping.BlogMapper;
import com.we.weblog.mapping.TagMapper;
import com.we.weblog.service.BlogService;
import com.we.weblog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class BlogServiceImpl implements BlogService {


    private BlogMapper blogMapper;
    private TagMapper tagMapper;

    /**
     * 构造函数
     * @param blogMapper
     */
    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper,TagMapper tagMapper) {
        this.tagMapper = tagMapper;
        this.blogMapper = blogMapper;
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
     * @param blog
     */
    @Override
    public void addBlog(Blog blog) {
        blog.setTime(new Date(System.currentTimeMillis()));
        blogMapper.insertBlog(blog);
        addBlogTags(blog.getTags(),blog.getBlogId());

    }

    @Override
    public void updateBlog(Blog blog) {
        blogMapper.updateBlog(blog);
        updateBlogTag(blog.getTags(),blog.getBlogId());
        updateBlogTag(blog.getTags(),blog.getBlogId());
    }

    @Override
    public void deleteBlogById(int id) {
        blogMapper.deleteBlogById(id);
    }


    @Override
    public List<Blog> showBlogs(int page) {
       page = page*10;
       List<Blog> blogs =  new ArrayList<>();
       blogs = blogMapper.getTenBlogs(page);

       return blogs;

    }

    @Override
    public int getBlogsPages() {
        return 0;
    }

    @Override
    public List<String> getAllTags() {
        return blogMapper.getAllTags();
    }

    @Override
    public Blog getBlogHtml() {
        return null;
    }

    @Override
    public List<Blog> getBlogsByTag(String tagName) {
        return null;
    }

    @Override
    public Blog getPreviousBlog(int blogId) {
        return null;
    }

    @Override
    public Blog getNextBlog(int blogId) {
        return null;
    }

    @Override
    public List<Blog> getYearBlog(int page) {
        return null;
    }

    @Override
    public int getNumberOfYearBlog() {
        return 0;
    }


}