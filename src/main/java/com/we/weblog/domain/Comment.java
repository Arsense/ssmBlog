package com.we.weblog.domain;

import com.we.weblog.domain.annotation.Email;
import com.we.weblog.domain.annotation.Length;
import com.we.weblog.domain.annotation.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *     博客分类
 * <pre/>
 */
public class Comment {
    /**
     * 评论id 自增
     */
    private int cid;
    /**
     * 所评论文章的ID
     */
    private int article_id;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 评论作者
     */
    @NotEmpty(message = "请输入评论作者")
    @Length(max = 30,message = "姓名过长")
    private String author;
    /**
     * 评论者邮箱
     */
    @NotEmpty(message = "请输入电子邮箱")
    @Email(message = "请输入正确的邮箱格式")
    private  String  email;
    /**
     * 评论者IP
     */
    private  String  ip;
    /**
     *  评论时间
     */
    private String commentDate;
    /**
     * 评论状态，0：正常，1：待审核，2：回收站
     */
    private Integer commentStatus = 1;
    /**
     * 评论内容
     */
    @NotEmpty(message = "请输入评论内容")
    @Length(max=2000,message = "评论过长")
    private  String  content;
    /**
     * 是否是博主的评论 0:不是 1:是
     */
    private Integer isAdmin;

    /**
     * 恢复父id
     */
    private int parent;

    /**
     * 当前评论下的所有子评论
     */
    private List<Comment> childComments;


    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
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


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Comment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<Comment> childComments) {
        this.childComments = childComments;
    }
}
