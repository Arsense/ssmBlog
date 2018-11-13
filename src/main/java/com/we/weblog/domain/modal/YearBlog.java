package com.we.weblog.domain.modal;

import com.we.weblog.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class YearBlog {


    private int year;
    private List<Post> yearContexts;

    public YearBlog(int year, ArrayList<Post> contexts) {
        this.year = year;
        this.yearContexts = contexts;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Post> getYearContexts() {
        return yearContexts;
    }


}
