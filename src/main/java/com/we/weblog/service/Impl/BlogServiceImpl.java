package com.we.weblog.service.Impl;


import com.we.weblog.mapping.BlogMapper;
import com.we.weblog.service.BlogService;
import com.we.weblog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {


    private BlogMapper blogMapper;


    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }


    @Override
    public void addBlog(Blog blog) {
        blogMapper.insertBlog(blog);
    }

    @Override
    public void updateBlog(Blog blog) {

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


}