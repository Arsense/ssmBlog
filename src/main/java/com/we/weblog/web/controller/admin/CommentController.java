package com.we.weblog.web.controller.admin;


import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.service.CommentService;
import com.we.weblog.web.controller.BaseController;
import com.we.weblog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *  评论管理页面
 */
@Controller
@RequestMapping("/admin/comments")
public class CommentController extends BaseController {

    private CommentService commentSerivce;

    @Autowired
    public CommentController(CommentService commentSerivce){
        this.commentSerivce = commentSerivce;
    }

    /**
     * 后台回复添加评论
     * @param comment
     * @return
     */
    @PostMapping("/send")
    @ResponseBody
    public  UIModel uiModel(@RequestBody Comment comment){
        return   UIModel.success();
    }

    /**
     * 前端 评论信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public UIModel list() {
        TableData tableData = new TableData();
        List<Comment> comments = commentSerivce.getAllComments();
        tableData.setDataItems(comments);
        tableData.setPage(false);

        FormModel formModel = new FormModel();
        formModel.createFormItem("cid").setHidden(false).setLabel("博客编号");
        formModel.createFormItem("content").setHidden(false).setLabel("评论内容");
        formModel.createFormItem("time").setHidden(false).setLabel("评论时间");
        formModel.createFormItem("author").setHidden(false).setLabel("评论人");
        formModel.createFormItem("email").setHidden(false).setLabel("邮箱");
        tableData.setFormItems(formModel.getFormItems());

        tableData.setTotalSize(commentSerivce.getCommentCount());

        return  UIModel.success().tableData(tableData);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public  UIModel deleteCommnets(@PathVariable("id") Integer commentId) {
        if (commentId <= 0 )
            return UIModel.fail().msg("删除id非法");
        int result  = commentSerivce.removeByCommentId(commentId);
        if (result > 0) {
            return UIModel.success().msg("删除成功");
        }
        return UIModel.fail().msg("删除失败");

    }

    /**
     * 回复 这里需要知道回复文章评论的ID和回复的消息
     * d
     * @return
     */
    @PostMapping("/reply/{id}")
    @ResponseBody
    public  UIModel replyComments(@RequestBody String text,@PathVariable("id") Integer cid) {

        if (text == null || text.equals("")) {
            return UIModel.fail().msg("请输入完成的回复");
        } else if (text.length() > 2000){
            return UIModel.fail().msg("请输入2000字以内的评论");
        }
        //查看该评论是否存在
        Comment comment  = commentSerivce.getCommentById(cid);
        if (comment== null) {
            return UIModel.fail().msg("评论的文章不存在");
        }
        //处理XSS
        text = cleanXSS(text);
        commentSerivce.replyComment(text,cid,comment);

        return UIModel.success().msg("回复成功");


    }



}
