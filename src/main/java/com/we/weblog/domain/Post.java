package com.we.weblog.domain;

import java.util.Date;

/**
 * <pre>
 *     博客分类
 * <pre/>
 */
public class Post {
    //   博客内容md 博客内容的html
    // 博客id
    private int   uid;
    //创建时间
    private Date   created;
    //博客名称
    private String   title;
    //博客标签
    private String   tags;
   //文章标签
    public String   article;
    //md内容
    private String  md;
    //发布时间
    private String  month;
    //文章类型
    private String  type;
    //缩略名
    private String  slug;
    //用了确认是草稿 还是发布的文章
    private String  publish;
    // 分类列表
    private String   categories;
    //浏览量
    private int   hits;
    // 0是已发布 1是草稿 2是回收站
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
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

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

}
