package com.we.weblog.service;

import com.we.weblog.domain.Category;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.YearBlog;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * @author tangwei
 * @date 2018/10/23 19:24
 */
public interface PostService {

    int getCategoryCount();
    List<Category> sortBlogsByCategories();
    List<String> getCategories();
    List<Category>  getBlogsFromTags(List<Post> contexts);
    int getLastestBlogId();
    List<String> getTagList(String tagString);
    Post getAboutme() throws Exception;
    void addBlogTags(String tags,int id);
    List<Post> getLastestBlogs();
    void updateBlogTag(String tags,int id);
    void updateBlog(Post context, int uid) throws SQLException;
    List<Post> showBlogs(int page);
    List<Post> getBlogsByTag(String tagName);
    Post getPreviousBlog(int uid);
    Post getNextBlog(int uid);
    List<YearBlog> getYearBlog(int page) throws IOException;
    List<YearBlog> sortBlogsByYears(List<Post> bloglist) throws IOException;
    int getTotalBlog();
    List<String> getAllKindTags();
    List<Post> getRecentBlogs(int limit);


    /**
     * 得到页面管理的信息
     * @return
     */
    List<Post> getArticlePages();

    /**
     * 将Date变成年月份
     * @param pages
     * @return
     */
    List<Post> sortContextDate(List<Post> pages);
     int deleteCatories(String name);
     void addOneHits(Post context);

    /**
     * 新增文章
     *
     * @param post Post
     * @return Post
     */
    void saveByPost(Post post) throws SQLException;

    /**
     * 根据编号删除文章
     *
     * @param postId postId
     * @return Post
     */
    Integer removeByPostId(Integer postId);


    /**
     * 修改文章状态
     *
     * @param postId postId
     * @param status status
     * @return Post
     */
    Post updatePostStatus(Long postId, Integer status);

    /**
     * 获取文章列表 不分页
     *
     * @param postType post or page
     * @return List
     */
    List<Post> findAllPosts(String postType);


    /**
     * 模糊查询文章
     *
     * @param keyWord  keyword
     * @return List
     */
    List<Post> searchPosts(String keyWord);

    /**
     * 根据编号查询文章
     *
     * @param postId postId
     * @return Post
     */
    Post findByPostId(int postId);

    /**
     * 查询前五条数据
     *
     * @return List
     */
    List<Post> findLastestPost();
}
