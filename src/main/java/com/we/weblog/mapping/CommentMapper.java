package com.we.weblog.mapping;


import com.we.weblog.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
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

}
