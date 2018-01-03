package com.we.weblog.domain;

import java.util.Date;

public class Blog {
    //   博客内容md 博客内容的html
    /**
     *  博客id
     */
    private int   blog_id;
    /**
     *  创建时间
     */
    private Date      date;
    /**
     *  博客名称
     */
    private String    title;
    /**
     *      博客标签
     */
    private String   tags;


    private String  md;

    private String month;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMdText() {
        return md;
    }

    public void setMdText(String md) {
        this.md = md;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String  article;



    public Integer getBlogId() {
        return blog_id;
    }

    public void setBlogId(Integer blogId) {
        this.blog_id = blog_id;
    }

    public Date getTime() {
        return date;
    }

    public void setTime(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }





}
