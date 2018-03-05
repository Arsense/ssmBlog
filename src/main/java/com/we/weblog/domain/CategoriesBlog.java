package com.we.weblog.domain;

import java.util.List;

public class CategoriesBlog {


    private  String category;
    private List<Context> blogs;

    public CategoriesBlog(String category, List<Context> blogs) {
        this.category = category;
        this.blogs = blogs;
    }

    public List<Context> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Context> blogs) {
        this.blogs = blogs;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
