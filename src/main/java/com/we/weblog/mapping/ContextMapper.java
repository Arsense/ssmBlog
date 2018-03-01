package com.we.weblog.mapping;


import com.we.weblog.domain.Context;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  处理博客信息的管理mapper
 */
@Repository
@Mapper
public interface ContextMapper {




    @Select({"select * from t_context where type = 'post'  order by uid asc limit 1 "})
    Context getblogId();

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
    @Select({"select count(*) from t_context where type = 'post'"})
    int getBlogNumber();

    /**
     *  插入博客 用于增加博客内容吧
     * @param context
     * @return
     */
    @Insert({"insert into t_context " +
            "(article,title,created,tags,md,type) " +
            "values (#{b.article},#{b.title},#{b.created},#{b.tags},#{b.md},#{b.type})"})
    @SelectKey(before=false,keyProperty="b.uid",resultType=Integer.class,
            statementType= StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    int insertBlog(@Param("b") Context context);

    /**
     *  删除博客
     * @param id
     * @return
     */
    @Delete({"delete from t_context where uid = #{id}"})
    int deleteBlogById(@Param("id") int id);

    /**
     * 批量查询博客  目前10个一次
     * @param count
     * @return
     */
    @Select({"select uid,title,created,tags,article,slug from t_context where type = 'post'  limit #{count}"})
    List<Context> getTenBlogs(@Param("count") int count);



    @Update("{ update t_context " +
            " set title = #{b.title}," +
            "set created = #{b.created}" +
            "set md = #{b.md}" +
            "set article=#{b.article}}")
    void updateBlog(@Param("b") Context context);


    @Select({"select dinstinct name from t_context"})
    @ResultType(String.class)
    List<String> getAllTags();



    @Select({"select uid,title,created,tags from t_context where type = 'post'  order by created desc limit #{p},12"})
    List<Context> selectBlogsByYear(@Param("p") int page);


    @Select({"select uid,title,created from t_context where type = 'post' order by created desc limit #{p},20"})
    List<Context> getNewBlogs(@Param("p") int page);

    @Select({"select uid,title,article,md,created,tags from t_context where uid = #{id} and type = 'post' "})
    Context getBlogById(@Param("id") int id);

    @Select({"select uid,title,tags,created from t_context where uid < #{id} and type = 'post'   order by uid desc limit 1"})
    Context getPreviousBlog(@Param("id") int id);

    @Select({"select uid,title,article,tags,created from t_context where uid > #{id} and type = 'post' order by uid asc limit 1"})
    Context getNextBlog(@Param("id") int id);





    @Select("select * from t_context where tags=#{tag}")
    List<Context> selectBlogByTag(@Param("tag") String tagName);




    @Select({"select * from t_context where type= #{type} order by uid desc"})
    List<Context> getPagesByType(@Param("type")String page);
}