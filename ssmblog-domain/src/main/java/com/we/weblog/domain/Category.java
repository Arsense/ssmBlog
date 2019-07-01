package com.we.weblog.domain;

import java.util.List;

/**
 * <pre>
 *     博客分类
 * <pre/>
 *
 */
public class Category {

    private  String category;

    private List<Post> blogs;

    public Category(String category, List<Post> blogs) {
        this.category = category;
        this.blogs = blogs;
    }

    public List<Post> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Post> blogs) {
        this.blogs = blogs;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
