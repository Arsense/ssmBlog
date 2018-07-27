package com.we.weblog.domain;




import com.we.weblog.domain.annotation.Email;
import com.we.weblog.domain.annotation.Length;
import com.we.weblog.domain.annotation.NotEmpty;

import java.util.Date;

/**
 * 评论
 */
public class Comment {

    //comment主键
    private  int        cid;

    //所评论文章的ID
    private  int        article_id;
    //创建时间
    private  Date       created;

    //评论作者
    @NotEmpty(message = "请输入评论作者")
    @Length(max = 30,message = "姓名过长")
    private  String     author;
    //评论者邮箱
    @NotEmpty(message = "请输入电子邮箱")
    @Email(message = "请输入正确的邮箱格式")
    private  String     email;
    //评论者IP
    private  String     ip;

    private String  time;

    //评论内容
    @NotEmpty(message = "请输入评论内容")
    @Length(max=2000,message = "评论过长")
    private  String     content;



    //恢复父id
    private int parent;



    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }




    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }
}
