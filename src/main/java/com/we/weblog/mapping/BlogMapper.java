package com.we.weblog.mapping;


import com.we.weblog.domain.Blog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *  处理博客信息的管理mapper
 */
@Repository
@Mapper
public interface BlogMapper {

    /**
     *  查找单个博客
     * @param id
     * @return
     */
    @Select({"select * from t_blog where id = #{id}"})
     Blog searchBlogById(@Param("id") int id);

    /**
     * 得到博客的总数量
      * @return
     */
    @Select({"select count(*) from t_blog"})
    int getBlogNumber();

    /**
     *  插入博客 用于增加博客内容吧
     * @param blog
     * @return
     */
    @Insert({"insert into t_blog " +
            "(article,title,date,tags,md) " +
            "values (#{b.article},#{b.title},#{b.date},#{b.tags},#{b.md})"})
    @SelectKey(before=false,keyProperty="b.blog_id",resultType=Integer.class,
            statementType= StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    int insertBlog(@Param("b") Blog blog);

    /**
     *  删除博客
     * @param id
     * @return
     */
    @Delete({"delete from t_blog where id = #{id}"})
    int deleteBlogById(@Param("id") int id);

    /**
     * 批量查询博客  目前10个一次
     * @param count
     * @return
     */
    @Select({"select blog_id,title,date,tags from t_blog limit #{count}"})
    List<Blog> getTenBlogs(@Param("count") int count);



    @Update("{ update t_blog " +
            " set title = #{b.title}," +
            "set date = #{b.date}" +
            "set md = #{b.md}" +
            "set article=#{b.article}}")
    void updateBlog(@Param("b") Blog blog);


    @Select({"select dinstinct name from t_blog"})
    @ResultType(String.class)
    List<String> getAllTags();



    @Select({"select blog_id,title,date,tags from t_blog order by date desc limit #{p},12"})
    List<Blog> selectBlogsByYear(@Param("p") int page);


    @Select({"select title,article from t_blog where blog_id = #{id}"})
    Blog getBlogById(@Param("id") int id);

    @Select({"select blog_id,title from t_blog where blog_id < #{id} order by blog_id desc limit 1"})
    Blog getPreviousBlog(@Param("id") int id);

    @Select({"select blog_id,title,article from t_blog where blog_id > #{id} order by blog_id desc limit 1"})
    Blog getNextBlog(@Param("id") int id);

    //distinct 不重复的
    @Select({"select distinct tag_name from t_tag "})
    @ResultType(String.class)
    List<String> selectTagkinds();



    @Select("select * from t_blog where tags=#{tag}")
    List<Blog> selectBlogByTag(@Param("tag") String tagName);
}