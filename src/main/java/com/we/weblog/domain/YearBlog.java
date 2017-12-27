package com.we.weblog.domain;

import java.util.List;

public class YearBlog {


    private int year;
    private List<Blog> yearBlogs;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Blog> getYearBlogs() {
        return yearBlogs;
    }

    public void setYearBlogs(List<Blog> yearBlogs) {
        this.yearBlogs = yearBlogs;
    }
}
