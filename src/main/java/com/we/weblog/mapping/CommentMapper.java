package com.we.weblog.mapping;


import com.we.weblog.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {




    @Select({"select comment_id,created,author,email,content from t_comments"})
    List<Comment> selectAllComments();



    @Select({"select count(*) from t_comments"})
    int getNumberOfComment();



    @Select({"select article_id,author,content,created from t_comments where article_id = #{uid} "})
    Comment getArticleById(@Param("uid") int uid);

    @Insert({"insert into from"})
    int addComment();

}
