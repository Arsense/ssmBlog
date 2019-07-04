package com.we.weblog.service.impl;


import com.we.weblog.domain.Comment;
import com.we.weblog.domain.result.Result;
import com.we.weblog.domain.util.TimeUtil;
import com.we.weblog.mapper.CommentMapper;
import com.we.weblog.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Resource
    private CommentMapper commentMapper;

    /**
     * 查询所有的评论，用于后台管理
     *
     * @return Page
     */
    @Override
    public Result findAllComments() {
        Result result = new Result();

        List<Comment> comments = null;
        try {
            comments = commentMapper.queryComment(new Comment());
            for (Comment comment:comments){
                comment.setCommentDate(
                        TimeUtil.getFormatClearToSecond(comment.getCreated()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setSuccess(true);
        result.setData(comments);
        return result;

    }

    @Override
    public Result findAllCommentsByStatus(int status) {
        Result result = new Result();
        Comment request = new Comment();
        request.setStatus(status);
        List<Comment> comments = null;
        try {
            comments = commentMapper.queryComment(request);
            for (Comment comment : comments) {
                comment.setCommentDate(
                        TimeUtil.getFormatClearToSecond(comment.getCreated()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setData(comments);
        result.setSuccess(true);
        return result;
    }


    /**
     * 新增评论
     *
     * @param comment comment
     */
    @Override
    public Result saveByComment(Comment comment) {
        Result result = new Result();
        try {
            int status = commentMapper.insertComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess(true);
        return result;
    }


    /**
     * 根据文章id获取评论
     * @return
     */
    @Override
    public Result findCommentByUid(int uid) {
        Result result = new Result();
        Comment request = new Comment();
        request.setArticle_id(uid);
        List<Comment> comments = commentMapper.queryComment(request);
        if(comments.isEmpty()){
            return result;
        }
        for(Comment comment:comments){
            comment.setCommentDate(TimeUtil.getFormatClearToDay(comment.getCreated()));
        }
        result.setData(comments);
        return result;
    }

    /**
     * 回复评论
     * @param messgae
     * @param cid
     * @param reply
     * @return
     */
    @Override
    public Result replyComment(String messgae, Integer cid, Comment reply) {
        Result result = new Result();

        Comment comment = new Comment();
        comment.setCreated(new Date(System.currentTimeMillis()));
        comment.setAuthor("admin");
        //后面再改吧
        comment.setEmail("admin@qq.com");
        comment.setContent("回复@" + reply.getAuthor() + "  " + messgae);
        comment.setParent(cid);
        commentMapper.insertComment(comment);
        return result;
    }

    @Override
    public Result updateCommentStatus(Integer commentId, Integer status) {
        commentMapper.updateByStatus(commentId, status);
        return new Result();
    }


    /**
     * 获取文章数量
     * @return
     */
    @Override
    public Integer getCommentCount() {
        return commentMapper.countComment();
    }

    /**
     * 查询评论byId
     * @param cid
     * @return
     */
    @Override
    public Result findCommentById(Integer cid) {
        Result result = new Result();
        Comment comment = new Comment();
        comment.setCid(cid);
        commentMapper.queryComment(comment);
        return result;
    }

    /**
     * 删除评论
     * @param cid
     * @return
     */
    @Override
    public Result removeByCommentId(Integer cid) {
        Result result = new Result();
        commentMapper.deleteComment(cid);
        return result;
    }




}
