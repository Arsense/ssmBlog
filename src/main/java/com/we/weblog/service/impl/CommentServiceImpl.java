package com.we.weblog.service.impl;


import com.we.weblog.domain.Comment;
import com.we.weblog.mapper.CommentMapper;
import com.we.weblog.service.CommentService;
import com.we.weblog.domain.util.TimeUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *     评论业务逻辑接口
 * </pre>
 * 评论管理Service
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * 查询所有的评论，用于后台管理
     *
     * @return Page
     */
    @Override
    public List<Comment> findAllComments() {
        List<Comment> comments = commentMapper.selectAllComments();
        for (Comment comment:comments){
            comment.setCommentDate(
                    TimeUtil.getFormatClearToSecond(comment.getCreated()));
        }
        return  comments;
    }

    @Override
    public List<Comment> findAllCommentsByStatus(int status) {
        List<Comment> comments = commentMapper.findAllCommentsByStatus(status);
        for (Comment comment:comments){
            comment.setCommentDate(
                    TimeUtil.getFormatClearToSecond(comment.getCreated()));
        }
        return  comments;
    }


    /**
     * 新增评论
     *
     * @param comment comment
     */
    @Override
    public Integer saveByComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }


    /**
     * 根据文章id获取评论
     * @return
     */
    @Override
    public List<Comment> findCommentByUid(int uid) {
        List<Comment> comments = commentMapper.getArticleById(uid);
        if(comments.isEmpty()){
            return comments;
        }
        for(Comment comment:comments){
            comment.setCommentDate(TimeUtil.getFormatClearToDay(comment.getCreated()));
        }
        return comments;
    }

    /**
     * 回复评论
     * @param messgae
     * @param cid
     * @param reply
     * @return
     */
    @Override
    public Integer replyComment(String messgae, Integer cid, Comment reply) {
        Comment comment = new Comment();
        comment.setCreated(new Date(System.currentTimeMillis()));
        comment.setAuthor("admin");
        //后面再改吧
        comment.setEmail("admin@qq.com");
//        comment.(reply.getArticle_id());
        comment.setContent("回复@" + reply.getAuthor() + "  " + messgae);
        comment.setParent(cid);

        return commentMapper.insertComment(comment);
    }

    @Override
    public void updateCommentStatus(Integer commentId, Integer status) {
        commentMapper.updateByStatus(commentId, status);
    }


    /**
     * 获取文章数量
     * @return
     */
    @Override
    public Integer getCommentCount() {
        return commentMapper.getNumberOfComment();
    }

    /**
     * 查询评论byId
     * @param cid
     * @return
     */
    @Override
    public Comment findCommentById(Integer cid) {
        return commentMapper.selectCommentById(cid);
    }

    /**
     * 删除评论
     * @param cid
     * @return
     */
    @Override
    public Integer removeByCommentId(Integer cid) {
        return commentMapper.deleteCommentById(cid);
    }




}
