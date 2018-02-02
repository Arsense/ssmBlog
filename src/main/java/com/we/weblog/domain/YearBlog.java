package com.we.weblog.domain;

import java.util.ArrayList;
import java.util.List;

public class YearBlog {


    private int year;
    private List<Context> yearContexts;

    public YearBlog(int year, ArrayList<Context> contexts) {
        this.year = year;
        this.yearContexts = contexts;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Context> getYearContexts() {
        return yearContexts;
    }




}
