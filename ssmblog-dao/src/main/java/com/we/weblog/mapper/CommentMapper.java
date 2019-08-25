package com.we.weblog.mapper;

import com.we.weblog.domain.Comment;
import com.we.weblog.domain.enums.CommentStatus;
import com.we.weblog.mapper.builder.CommentSqlBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {


    @SelectProvider(type = CommentSqlBuilder.class, method = "buildCommentQuery")
    List<Comment> queryComment(Comment comment);

    @SelectProvider(type = CommentSqlBuilder.class, method = "buildCountCommentQuery")
    int countComment();


    @UpdateProvider(type = CommentSqlBuilder.class, method = "buildUpdate")
    void updateByStatus(@Param("id") Integer commentId, @Param("status") Integer status);


    @InsertProvider(type = CommentSqlBuilder.class, method = "buildInsert")
    int insertComment(@Param("c") Comment comment);


    @DeleteProvider(type = CommentSqlBuilder.class, method = "buildDelete")
    int deleteComment(@Param("id") Integer cid);


}
