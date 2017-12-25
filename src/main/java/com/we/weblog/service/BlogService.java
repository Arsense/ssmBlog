package com.we.weblog.service;

import com.we.weblog.domain.Blog;

import java.util.List;

public interface BlogService {


    void  addBlog(Blog blog);               //添加博客
    void  updateBlog(Blog blog);            //修改博客
    void  deleteBlogById(int id);           //删除博客
    List<Blog>  showBlogs(int page);                //显示博客



}
