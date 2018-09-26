package com.we.weblog.controller.admin.table;

import com.vue.adminlte4j.annotation.UIFormItem;

import java.io.Serializable;

/**
 * @author tangwei
 * @date 2018/9/25 23:17
 */
public class CommentForm implements Serializable {

    @UIFormItem(label = "评论id" )
    private String cid      ;

    @UIFormItem(label = "评论内容" )
    private String content      ;

    @UIFormItem(label = "评论人" )
    private String author      ;

    @UIFormItem(label = "评论时间" )
    private String time      ;

    @UIFormItem(label = "评论人邮箱" )
    private String email      ;


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
