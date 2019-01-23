package com.we.weblog.web.controller;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.domain.Comment;
import com.we.weblog.domain.util.AddressUtil;
import com.we.weblog.service.CommentService;
import com.we.weblog.web.controller.core.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author tangwei
 * @date 2018/12/19 16:55
 */
@Controller
@RequestMapping("comments")
public class CommentController extends BaseController{

    @Resource
    private CommentService commentSerivce;

    /**
     * 添加评论
     * @param
     * @return
     */
    @PostMapping("/send")
    @ResponseBody
    public UIModel addComment(@RequestBody Comment comment ){
        if (StringUtils.isEmpty(comment) || comment.getArticle_id() <= 0 ) {
            return UIModel.fail().msg("评论失败,    输入信息有误");
        } else if (!comment.getEmail().contains("@")) {
            return UIModel.fail().msg("邮箱格式不正确");
        } else if (comment.getContent().length() < 5) {
            return UIModel.fail().msg("您的评论太短");
        } else if (comment.getContent() == null) {
            return UIModel.fail().msg("您的评论不能为空");
        }
        //处理XSS
        comment.setContent(cleanXSS(comment.getContent()));
        comment.setCreated(new Date(System.currentTimeMillis()));
        comment.setIp(AddressUtil.getIpAddress(request));
        comment.setIsAdmin(0);
        try {
            if (comment.getParent() > 0) {
                comment = commentSerivce.findCommentById(comment.getParent());
                String lastContent = "回复" + comment.getCid() + "'>@" + comment.getAuthor() + ":";
                comment.setContent(lastContent + comment.getContent());
            }
//             commentSerivce.saveComment(comment);
        } catch (Exception e) {
            return UIModel.fail().msg("评论失败");
        }
        return UIModel.success().msg("评论已经提交，待博主审核");


    }
}
