package com.we.weblog.mapper;


import com.we.weblog.domain.Post;
import com.we.weblog.mapper.builder.PostSqlBuilder;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  处理博客信息的管理mapper
 */
@Repository
@Mapper
public interface PostMapper {

    /**
     * 查询文章接口
     * @param post
     * @return
     */
    @SelectProvider(type = PostSqlBuilder.class, method = "buildGetPostQuery")
    List<Post> queryPost(Post post);

    @SelectProvider(type = PostSqlBuilder.class, method = "buildGetlimitPostQuery")
    List<Post> querylimitPost(@Param("post") Post post);

    /**
     * 分类标签查询 这里 in not null去除空列表
     * @return
     */
    @SelectProvider(type = PostSqlBuilder.class, method = "buildGetCategoryQuery")
    List<String> findAllCategory();

    /**
     * 得到博客的总数量
     * @return
     */
    @SelectProvider(type = PostSqlBuilder.class, method = "buildCountPostQuery")
    int countPost(Post post);


    @UpdateProvider(type = PostSqlBuilder.class, method = "buildUpdate")
    int updateVisit( Post post);

    /**
     * todo 这个其实也可以和第一个合并 研究一下
     * 批量查询博客  目前10个一次
     * @param count
     * @return
     */
    @SelectProvider(type = PostSqlBuilder.class, method = "buildRecentPostsQuery")
    List<Post> findRecentPosts(@Param("count") int count);


    @SelectProvider(type = PostSqlBuilder.class, method = "buildGetPostByCreated")
    List<Post> findPostByYearAndMonth(@Param("p") int page);


    @UpdateProvider(type = PostSqlBuilder.class, method = "buildUpdate")
    int updatePost(Post post);

    /**
     *  删除博客
     * @param id
     * @return
     */
    @UpdateProvider(type = PostSqlBuilder.class, method = "buildDelete")
    int removePost(@Param("id") int id);

    /**
     * 标签页面删除相关数据
     * @param categoryName
     */
    @UpdateProvider(type = PostSqlBuilder.class, method = "buildDeleteByCategory")
    int removePostCategory(@Param("cate") String categoryName);


    /**
     *  插入博客 用于增加博客内容吧
     * @param post
     * @return
     */
    @InsertProvider(type = PostSqlBuilder.class, method = "buildInsert")
    @SelectKey(before=false,keyProperty="b.uid",resultType=Integer.class,
            statementType= StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    int savePost(@Param("b") Post post);


    @SelectProvider(type = PostSqlBuilder.class, method = "buildGetPreviousQuery")
    Post findPreviousPost(@Param("id") int id);

    @SelectProvider(type = PostSqlBuilder.class, method = "buildGetNextQuery")
    Post findNextPost(@Param("id") int id);




}