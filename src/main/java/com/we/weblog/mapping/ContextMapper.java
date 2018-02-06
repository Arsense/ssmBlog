package com.we.weblog.mapping;


import com.we.weblog.domain.Context;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *  处理博客信息的管理mapper
 */
@Repository
@Mapper
public interface ContextMapper {

    /**
     *  查找单个博客
     * @param id
     * @return
     */
    @Select({"select * from t_context where id = #{id}"})
    Context searchBlogById(@Param("id") int id);

    /**
     * 得到博客的总数量
      * @return
     */
    @Select({"select count(*) from t_context"})
    int getBlogNumber();

    /**
     *  插入博客 用于增加博客内容吧
     * @param context
     * @return
     */
    @Insert({"insert into t_context " +
            "(article,title,date,tags,md) " +
            "values (#{b.article},#{b.title},#{b.date},#{b.tags},#{b.md})"})
    @SelectKey(before=false,keyProperty="b.blog_id",resultType=Integer.class,
            statementType= StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    int insertBlog(@Param("b") Context context);

    /**
     *  删除博客
     * @param id
     * @return
     */
    @Delete({"delete from t_context where blog_id = #{id}"})
    int deleteBlogById(@Param("id") int id);

    /**
     * 批量查询博客  目前10个一次
     * @param count
     * @return
     */
    @Select({"select blog_id,title,date,tags from t_context limit #{count}"})
    List<Context> getTenBlogs(@Param("count") int count);



    @Update("{ update t_context " +
            " set title = #{b.title}," +
            "set date = #{b.date}" +
            "set md = #{b.md}" +
            "set article=#{b.article}}")
    void updateBlog(@Param("b") Context context);


    @Select({"select dinstinct name from t_context"})
    @ResultType(String.class)
    List<String> getAllTags();



    @Select({"select blog_id,title,date,tags from t_context order by date desc limit #{p},12"})
    List<Context> selectBlogsByYear(@Param("p") int page);


    @Select({"select blog_id,title,date from t_context order by date desc limit #{p},10"})
    List<Context> getNewBlogs(@Param("p") int page);

    @Select({"select title,article,md from t_context where blog_id = #{id}"})
    Context getBlogById(@Param("id") int id);

    @Select({"select blog_id,title from t_context where blog_id < #{id} order by blog_id desc limit 1"})
    Context getPreviousBlog(@Param("id") int id);

    @Select({"select blog_id,title,article from t_context where blog_id > #{id} order by blog_id desc limit 1"})
    Context getNextBlog(@Param("id") int id);





    @Select("select * from t_blog where tags=#{tag}")
    List<Context> selectBlogByTag(@Param("tag") String tagName);
}