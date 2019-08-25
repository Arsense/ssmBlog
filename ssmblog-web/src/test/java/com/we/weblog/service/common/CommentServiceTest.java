package com.we.weblog.service.common;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.Comment;
import com.we.weblog.service.CommentService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Clay
 * @date 2019/3/28 14:10
 */
public class CommentServiceTest extends BaseTest {


    @Resource
    private CommentService commentService;


    @Test
    public void findAllComments() {
        commentService.findAllComments();
    }

    @Test
    public void findCommentById() {
        commentService.findCommentById(1);

    }

    @Test
    public void saveByComment() {
        commentService.saveByComment(new Comment());

    }

    @Test
    public void getCommentCount() {
        commentService.getCommentCount();

    }

    @Test
    public void findCommentByUid() {
        commentService.findCommentByUid(1);

    }

    @Test
    public void replyComment() {
        commentService.replyComment("1",1,new Comment());

    }

    @Test
    public void updateCommentStatus() {

        commentService.updateCommentStatus(1, 1);

    }
}