package com.we.weblog.mapper;

import com.we.weblog.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface CommentMapper {


    @Select({"select commentId,post_id,created,author,email,content from t_comments"})
    List<Comment> selectAllComments();

    @Select({"select count(*) from t_comments"})
    int getNumberOfComment();

    @Select({"select article_id,author,content,created from t_comments where article_id = #{uid} "})
    List<Comment> getArticleById(@Param("uid") int uid);

    @Insert({"insert into t_comments (post_id,created,author,email,ip,content,parent) "+
    "values (#{c.post_id},#{c.created},#{c.author},#{c.email},#{c.ip},#{c.content},#{c.parent})"})
    int insertComment(@Param("c") Comment comment);


    @Delete({"delete from t_comments where commentId = #{id}"})
    int deleteCommentById(@Param("id")Integer cid);


    @Select({"select * from t_comments where commentId = #{c}"})
    Comment selectCommentById(@Param("c") Integer cid);

}
