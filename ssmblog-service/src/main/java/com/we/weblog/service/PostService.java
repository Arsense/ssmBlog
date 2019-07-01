package com.we.weblog.service;


import com.we.weblog.domain.Category;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.common.Result;
import com.we.weblog.domain.modal.YearBlog;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author tangwei
 * @date 2018/10/23 19:24
 */
public interface PostService {

    /**
     * 新增文章
     *
     * @param post Post
     * @return Post
     */
    Result saveByPost(Post post) throws SQLException;

    /**
     * 根据编号删除文章
     *
     * @param postId postId
     * @return Post
     */
    Result removeByPostId(Integer postId);


    Result findByTagName(String tagName);

    /**
     * 更新博客访问量
     *
     * @param context
     */
    Result updatePostVisit(Post context);
    /**
     * 得到分类总数
     *
     * @return
     */
    Result getCategoryCount();

    /**
     * 获取最新博客Id
     * @return
     */
    Result getLastestBlogId();

    /**
     * 根据分类查询整理博客
     *
     * @return
     */
    Result sortBlogsByCategories();

    /***
     * 查询关于我
     * @return
     * @throws Exception
     */
    Result findAuthor() throws Exception;

    /**
     * 更新博客
     * @param context
     * @param uid
     * @throws SQLException
     */
    Result updatePost(Post context, int uid) throws SQLException;

    /**
     * 根据标签名查询博客
     * @param tagName
     * @return
     */
    Result findPostsByTagName(String tagName);

    /**
     * 根据年份和月份查询文章
     *
     * @return List
     */
    Result findPostByYearAndMonth(int page) throws IOException;

    /**
     * 获取博客总数量
     * @return
     */
    Result findPostCount();

    /**
     * 得到页面管理的信息
     * @return
     */
    Result getArticlePages();

    /**
     * 清楚博客里面分类字段
     * @param name
     * @return
     */
    Result removePostCategory(String name);



    /**
     * 查询Id之后的一篇文章
     *
     * @return List
     */
    Result findNextPost(int uid);

    /**
     * 查询Id之前的一篇文章
     *
     * @return List
     */
    Result findPreviousPost(int uid);

    /**
     * 修改文章状态
     *
     * @param postId postId
     * @param status status
     * @return Post
     */
    Result updatePostStatus(Integer postId, Integer status);
    /**
     * 获取文章列表 不分页
     *
     * @param page post or page
     * @return List
     */
    Result findAllPosts(int page);


    /**
     * 获取所有文章
     *
     * @return List
     */
    Result findAllPosts();


    /**
     * 根据文章状态获取所有文章
     *
     * @return List
     */
    Result findAllPostsByStatus(int status);



    /**
     * 根据编号查询文章
     *
     * @param postId postId
     * @return Post
     */
    Result findByPostId(int postId);

    /**
     * 查询前五条数据
     *
     * @return List
     */
    Result findLastestPost(int limit);
}
