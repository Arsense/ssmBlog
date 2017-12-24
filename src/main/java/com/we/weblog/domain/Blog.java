package com.we.weblog.domain;

import java.util.Date;

public class Blog {
    //   博客内容md 博客内容的html
    /**
     *  博客id
     */
    public Integer   blogId;
    /**
     *  创建时间
     */
    public Date      date;
    /**
     *  博客名称
     */
    public String    title;
    /**
     *      博客标签
     */
    public String   tags;





    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
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
