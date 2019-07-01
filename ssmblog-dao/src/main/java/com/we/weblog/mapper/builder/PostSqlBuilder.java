package com.we.weblog.mapper.builder;

import com.we.weblog.domain.Post;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Clay
 * @date 2019/7/1 18:31
 */
public class PostSqlBuilder {


    public String buildGetPostQuery(Post post){
        return new SQL(){{
            SELECT("*");
            FROM("hexo_post");
            if (null != post.getUid()) {
                WHERE("uid = #{uid}");
            }
            if (null != post.getTags()) {
                WHERE("tags = #{tagName}");
            }
            if (null != post.getType()) {
                WHERE("type = 'post'");
            }
            if (null != post.getStatus()) {
                WHERE("status = #{status}");
            }
            if(null != post.getTitle()) {
                WHERE("title = #{title}");
            }
            ORDER_BY("uid asc");
        }}.toString();
    }


    public String buildGetCategoryQuery(){
        return "select DISTINCT categories FROM hexo_post  where categories is not null";
    }

    public String buildCountPostQuery(Post post){
        return new SQL(){{
            SELECT("count(*)");
            FROM("hexo_post");
            if (null != post.getUid()) {
                WHERE("uid = #{uid}");
            }
            if (null != post.getTags()) {
                WHERE("tags = #{tagName}");
            }
            if (null != post.getType()) {
                WHERE("type = 'post'");
            }
            if (null != post.getStatus()) {
                WHERE("status = #{status}");
            }
            if(null != post.getTitle()) {
                WHERE("title = #{title}");
            }
            ORDER_BY("uid asc");
        }}.toString();
    }

    public String buildRecentPostsQuery(){
        return "select uid,title,created,tags,article" + ",slug,hits from hexo_post where type = 'post'  limit #{count}";
    }



}
