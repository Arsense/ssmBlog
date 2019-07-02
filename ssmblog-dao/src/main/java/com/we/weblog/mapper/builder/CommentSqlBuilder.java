package com.we.weblog.mapper.builder;

import com.we.weblog.domain.Comment;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Clay
 * @date 2019/7/1 23:57
 */
public class CommentSqlBuilder {

    public String buildCommentQuery(Comment comment) {
        return new SQL() {{
            SELECT("select cid,article_id,created,author,email,content");
            FROM("hexo_comment");
            if (comment.getCommentStatus() != null) {
              WHERE("status = #{status}");
            }
            if (comment.getCid() > 0) {
                WHERE("cid = #{cid}");
            }
            if (comment.getArticle_id() > 0) {
                WHERE("article_id = #{uid}");
            }
        }}.toString();
    }
    public String buildCountCommentQuery() {
        return "select count(*) from hexo_comment";
    }


    public String buildUpdate(){
       return "update hexo_comment set status = #{status} where cid = #{id}";
    }
    public String buildDelete() { return "delete from hexo_comment where cid = #{id}"; }

    /**
     * todo 优化
     * @return
     */
    public String buildInsert() {
        return "insert into hexo_comment (article_id,created,author,email,ip,content,parent) "+
                "values (#{c.article_id},#{c.created},#{c.author},#{c.email},#{c.ip},#{c.content},#{c.parent})";
    }



}
