package com.we.weblog.service;

import com.we.weblog.domain.Blog;

import java.util.List;

public interface BlogService {


    void  addBlog(Blog blog);               //添加博客
    void  updateBlog(Blog blog);            //修改博客
    void  deleteBlogById(int id);           //删除博客
    List<Blog>  showBlogs(int page);        //显示博客
    int getBlogsPages();                    //获得所有博客的页数
    List<String> getAllTags();              //获得所有的标签分类
    Blog getBlogHtml();                     //获得前端html
    List<Blog> getBlogsByTag(String tagName);   //根据标签获得博客



    Blog getPreviousBlog(int blogId);
    Blog getNextBlog(int blogId);


    //处理YearBlog
    List<Blog> getYearBlog(int page);
    int getNumberOfYearBlog();
}
