package com.we.weblog.mapping;


import com.we.weblog.domain.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
            "(b.title,b.data,b.tags) " +
            "values (#{b.title},#{b.data},#{b.tags})"})
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
    @Select({"select title,date,tags from t_blog limit #{count}"})
    List<Blog> getTenBlogs(@Param("count") int count);



}
