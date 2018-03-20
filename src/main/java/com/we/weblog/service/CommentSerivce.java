package com.we.weblog.service;


import com.we.weblog.domain.Comment;
import com.we.weblog.mapping.CommentMapper;
import com.we.weblog.tool.TimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentSerivce {


    private CommentMapper commentMapper;

    @Autowired
    public CommentSerivce(CommentMapper commentMapper){
        this.commentMapper = commentMapper;
    }


    /**
     * 得到所有的评论 先简单处理下
     * @return
     */
   public List<Comment> getComments(){

     List<Comment> comments = commentMapper.selectAllComments();
     for (Comment comment:comments){
         comment.setTime(TimeTool.getFormatClearToSecond(comment.getCreated()));
     }
     return  comments;
   }


   public int getCounts(){
       return commentMapper.getNumberOfComment();
   }



}
