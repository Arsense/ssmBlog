package com.we.weblog.controller.admin;


import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.controller.BaseController;
import com.we.weblog.domain.Comment;
import com.we.weblog.service.CommentSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 *  评论管理页面
 */
@Controller
@RequestMapping("/admin/comments")
public class CommentController extends BaseController {

    private CommentSerivce commentSerivce;

    @Autowired
    public CommentController(CommentSerivce commentSerivce){
        this.commentSerivce = commentSerivce;
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @PostMapping("/send")
    @ResponseBody
    public  UIModel uiModel(@RequestBody Comment comment){
        UIModel uiModel = new UIModel() ;
        return   uiModel;
    }

    /**
     * 前端 评论信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public UIModel list() {
        UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData();
        List<Comment> comments=commentSerivce.getComments();
        tableData.setDataItems(comments);
        tableData.setPage(false);

        FormModel formModel = new FormModel();
        formModel.createFormItem("cid").setHidden(false);
        formModel.createFormItem("content").setHidden(false);
        formModel.createFormItem("author").setHidden(false);
        formModel.createFormItem("time").setHidden(false);
        formModel.createFormItem("email").setHidden(false);
        tableData.setFormItems(formModel.getFormItems());

        tableData.setTotalSize(commentSerivce.getCounts());
        tableData.setTotalSize(10);

        return  UIModel.success().tableData(tableData);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public  UIModel deleteCommnets(@PathVariable("id") Integer commentId){

        if (commentId <= 0 )
            return UIModel.fail().setMsg("删除id非法");

        int result  = commentSerivce.deleteComment(commentId);

        if (result > 0) {
            return UIModel.success().setMsg("删除成功");
        }
        return UIModel.fail().setMsg("删除失败");

    }


    /**
     * 回复 这里需要知道回复文章评论的ID和回复的消息
     * d
     * @return
     */
    @PostMapping("/reply/{id}")
    @ResponseBody
    public  UIModel replyComments(@RequestBody String text,@PathVariable("id") Integer cid){

        if (text == null || text.equals("")) {
            return UIModel.fail().setMsg("请输入完成的回复");
        } else if (text.length() > 2000){
            return UIModel.fail().setMsg("请输入2000字以内的评论");
        }
        //查看该评论是否存在
        Comment comment  = commentSerivce.findComment(cid);
        if (comment== null) {
            return UIModel.fail().setMsg("评论的文章不存在");
        }
        //处理XSS
        text = cleanXSS(text);
        commentSerivce.replyMessage(text,cid,comment);

        return UIModel.success().setMsg("回复成功");


    }



}
