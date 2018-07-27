package com.we.weblog.service;


import com.we.weblog.domain.Comment;
import com.we.weblog.domain.Log;
import com.we.weblog.mapping.CommentMapper;
import com.we.weblog.tool.IpTool;
import com.we.weblog.tool.TimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

   public List<Comment> getCommentByArticleId(int uid){
       List<Comment> comments = commentMapper.getArticleById(uid);

       if(comments.isEmpty()){
           return comments;
       }
       for(Comment comment:comments){
           comment.setTime(TimeTool.getFormatClearToDay(comment.getCreated()));
       }

       return comments;
   }

    /**
     * 添加评论
     * @param comment
     * @param request
     * @return
     */
   public int addComments(Comment comment, HttpServletRequest request){


       comment.setCreated(new Date(System.currentTimeMillis()));
       comment.setIp(IpTool.getIpAddress(request));

       int result = commentMapper.insertComment(comment);
       return result;
   }

    /**
     * 删除评论
      * @param cid
     * @return
     */
   public int deleteComment(Integer cid) {
       return commentMapper.deleteCommentById(cid);
   }



   public void replyMessage(String messgae,Integer cid,Comment reply){
       Comment comment = new Comment();

       comment.setCreated(new Date(System.currentTimeMillis()));
       comment.setAuthor("admin");
       //后面再改吧
       comment.setEmail("admin@qq.com");
       comment.setArticle_id(reply.getArticle_id());
       comment.setContent("回复@"+reply.getAuthor()+"  "+messgae);
       comment.setParent(cid);

       commentMapper.insertComment(comment);

   }


   public  Comment findComment(Integer cid){

       return commentMapper.selectCommentById(cid);
   }

}
