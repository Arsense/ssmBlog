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
            ORDER_BY("uid desc");
        }}.toString();
    }
    public String buildGetlimitPostQuery(){
                return "select * from hexo_post limit 1,10 ";

//        return buildGetPostQuery(post) + " limit 1,#{limit}" ;
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


    public String buildUpdate(Post post) {
        return new SQL(){{
            UPDATE("hexo_post");
            if(post.getStatus() != null) {
                SET("status = #{status}");
            }
            if(post.getTitle() != null) {
                SET("title = #{title}");
            }
            if(post.getMd() != null) {
                SET("md = #{md}");
            }
            if(post.getSlug() != null) {
                SET("slug = #{slug}");
            }
            if(post.getCategories() != null) {
                SET("categories = #{categories}");
            }
            if(post.getTags() != null) {
                SET("tags = #{tags}");
            }
            if(post.getArticle() != null) {
                SET("article = #{article}");
            }
            if(post.getHits() > 0) {
                SET("hits = #{hits}");
            }
            if(post.getUid() != null) {
                WHERE("uid = #{uid}");
            }

        }}.toString();

    }



    public String buildDelete() { return "delete from hexo_post where uid = #{id}"; }


    public String buildDeleteByCategory() {
        return "update hexo_post set categories = null where categories = #{cate}";
    }


    public String buildGetPostByCreated() {
        //todo 优化 看不能合并
        return "select uid,title,created,tags from hexo_post " +
                "where type = 'post'  order by created desc limit #{p},12";
    }


    public String buildGetPreviousQuery() {
        return "select uid,title,tags,created from hexo_post " + "where uid < #{id} and type = 'post'   order by uid desc limit 1";
    }

    public String buildGetNextQuery() {
        return "select uid,title,article,tags,created " + "from hexo_post where uid > #{id} and type = 'post' order by uid asc limit 1";
    }

    public String buildInsert() {
        return "insert into hexo_post " +
                "(article,title,created,tags,md,type,slug,publish,categories) " +
                "values (#{b.article},#{b.title},#{b.created},#{b.tags},#{b.md}" +
                ",#{b.type},#{b.slug},#{b.publish},#{b.categories})" ;

    }

}
