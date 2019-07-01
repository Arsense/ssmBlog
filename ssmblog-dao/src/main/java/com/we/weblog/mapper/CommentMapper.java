package com.we.weblog.mapper;

import com.we.weblog.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {


    @Select({"select cid,article_id,created,author,email,content from hexo_comment"})
    List<Comment> selectAllComments();


    @Update({"update hexo_comment set status = #{status} where cid = #{id}"})
    void updateByStatus(@Param("id") Integer commentId, @Param("status") Integer status);

    @Select({"select cid,article_id,created,author,email,content from hexo_comment where status = #{status}"})
    List<Comment> findAllCommentsByStatus(@Param("status") int status);


    @Select({"select count(*) from hexo_comment"})
    int getNumberOfComment();

    @Select({"select article_id,author,content,created from hexo_comment where article_id = #{uid} "})
    List<Comment> getArticleById(@Param("uid") int uid);

    @Insert({"insert into hexo_comment (article_id,created,author,email,ip,content,parent) "+
    "values (#{c.article_id},#{c.created},#{c.author},#{c.email},#{c.ip},#{c.content},#{c.parent})"})
    int insertComment(@Param("c") Comment comment);


    @Delete({"delete from hexo_comment where cid = #{id}"})
    int deleteCommentById(@Param("id") Integer cid);


    @Select({"select * from hexo_comment where cid = #{c}"})
    Comment selectCommentById(@Param("c") Integer cid);

}
