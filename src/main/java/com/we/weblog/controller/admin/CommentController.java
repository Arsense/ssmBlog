package com.we.weblog.controller.admin;


import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
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
    @GetMapping("/table")
    @ResponseBody
    Map<String,Object> getAllComments() {

        UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData() ;

        tableData.configDisplayColumn(TableData.createColumn("cid" , "评论id" ));
        tableData.configDisplayColumn(TableData.createColumn("content" , "评论内容" ));
        tableData.configDisplayColumn(TableData.createColumn("author" , "评论人") );
        tableData.configDisplayColumn(TableData.createColumn("time" , "评论时间" ));
        tableData.configDisplayColumn(TableData.createColumn("email" , "评论人邮箱" ));

        //遍历查询数据库
        List<Comment> comments=commentSerivce.getComments();
        for (Comment comment : comments) {
            tableData.addData(comment);
        }

        tableData.setTotalSize(commentSerivce.getCounts());
        tableData.setTotalSize(10);
        uiModel.tableData(tableData);

        return uiModel ;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public  UIModel deleteCommnets(@PathVariable("id") Integer commentId){

        if(commentId <= 0 )
            return UIModel.fail().setMsg("删除id非法");

        int result  = commentSerivce.deleteComment(commentId);

        if(result > 0){
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

        if(text == null || text.equals("")){
            return UIModel.fail().setMsg("请输入完成的回复");
        }else if(text.length() > 2000){
            return UIModel.fail().setMsg("请输入2000字以内的评论");
        }

        //查看该评论是否存在
        Comment comment  = commentSerivce.findComment(cid);

        if(comment== null){
            return UIModel.fail().setMsg("评论的文章不存在");
        }
        //处理XSS
        text = cleanXSS(text);

       commentSerivce.replyMessage(text,cid,comment);


        return UIModel.success().setMsg("回复成功");


    }



}
