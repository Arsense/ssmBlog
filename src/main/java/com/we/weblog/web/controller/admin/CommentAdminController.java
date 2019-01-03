package com.we.weblog.web.controller.admin;


import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.enums.CommentStatus;
import com.we.weblog.domain.enums.PostStatus;
import com.we.weblog.domain.modal.CommentConfigQuery;
import com.we.weblog.domain.User;
import com.we.weblog.domain.util.BaseConfigUtil;
import com.we.weblog.service.CommentService;
import com.we.weblog.service.MailService;
import com.we.weblog.service.PostService;
import com.we.weblog.web.controller.core.BaseController;
import com.we.weblog.domain.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <pre>
 *     公共常量
 * </pre>
 *  评论管理页面
 */
@Controller
@RequestMapping("/admin/comments")
public class CommentAdminController extends BaseController {

    @Resource
    private CommentService commentService;
    @Resource
    private PostService postService;
    @Resource
    private MailService mailService;

    /**
     * 获得配置的查询模型
     *
     * @return
     */
    @RequestMapping("model")
    @ResponseBody
    public UIModel getOrderListModel() {
        return UIModel.success().formData(new CommentConfigQuery(), CommentConfigQuery.class);
    }

    /**
     * 前端 评论信息
     */
    @RequestMapping("/list")
    @ResponseBody
    public UIModel list(@RequestParam(value = "status", defaultValue = "0") String status,int currentPage) {
        TableData tableData = new TableData();
        List<Comment> comments = commentService.findAllCommentsByStatus(Integer.parseInt(status));
        tableData.setDataItems(comments);
        tableData.setPage(true);
        tableData.setPageSize(15);
        FormModel formModel = new FormModel();
        formModel.createFormItem("cid").setHidden(false).setLabel("评论Id");
        formModel.createFormItem("content").setHidden(false).setLabel("评论内容");
        formModel.createFormItem("time").setHidden(false).setLabel("评论时间");
        formModel.createFormItem("author").setHidden(false).setLabel("评论人");
        formModel.createFormItem("email").setHidden(false).setLabel("邮箱");
        tableData.setFormItems(formModel.getFormItems());
        tableData.setTotalSize(commentService.getCommentCount());

        return  UIModel.success().tableData(tableData);
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public  UIModel removeComment(@PathVariable("id") Integer commentId) {
        if (commentId <= 0 )
            return UIModel.fail().msg("删除id非法");
        int result  = commentService.removeByCommentId(commentId);
        if (result > 0) {
            return UIModel.success().msg("删除成功");
        }
        return UIModel.fail().msg("删除失败");
    }

    /**
     * 回复 这里需要知道回复文章评论的ID和回复的消息
     *
     *
     */
    @PostMapping("/reply/{id}")
    @ResponseBody
    public UIModel replyComments(@RequestBody String text,@PathVariable("id") Integer cid) {
        if (text == null || text.equals("")) {
            return UIModel.fail().msg("请输入完成的回复");
        } else if (text.length() > 2000){
            return UIModel.fail().msg("请输入2000字以内的评论");
        }
        //查看该评论是否存在
        Comment comment  = commentService.findCommentById(cid);
        if (comment== null) {
            return UIModel.fail().msg("评论的文章不存在");
        }
        //处理XSS
        text = cleanXSS(text);
        commentService.replyComment(text,cid,comment);
        return UIModel.success().msg("回复成功");
    }


    /**
     * 处理评论为发布状态
     *
     * @param commentId 文章编号
     * @return 重定向到/admin/posts
     */
    @GetMapping(value = "/revert/{id}")
    @ResponseBody
    public UIModel moveToPublish(@PathVariable("id")Integer commentId) {
        try {
            commentService.updateCommentStatus(commentId, PostStatus.PUBLISHED.getCode());
            logger.info("编号为" + commentId + "的文章已改变为发布状态");
        } catch (Exception e) {
            logger.error("发布文章失败：{}", e.getMessage());
        }
        return UIModel.success().msg("更改成功");
    }
    /**
     * 将评论移到回收站
     *
     * @return
     */
    @GetMapping(value = "/throw/{id}")
    @ResponseBody
    public UIModel moveToTrash(@PathVariable("id") Integer commentId) {
        try {
            commentService.updateCommentStatus(commentId, CommentStatus.RECYCLE.getCode());
            logger.info("编号为" + commentId + "的评论已被移到回收站");
        } catch (Exception e) {
            logger.error("删除评论到回收站失败：{}", e.getMessage());
        }
        return UIModel.success().msg("删除成功");
    }





    /**
     * 管理员回复评论
     *
     * @param commentId      被回复的评论
     * @param commentContent 回复的内容
     * @return 重定向到/admin/comments
     */
    @PostMapping(value = "/reply")
    public String replyComment(@RequestParam("commentId") Long commentId,
                               @RequestParam("postId") int postId,
                               @RequestParam("commentContent") String commentContent,
                               @RequestParam("userAgent") String userAgent,
                               HttpServletRequest request,
                               HttpSession session) {
        try {
            Post post = postService.findByPostId(postId);

            //博主信息
            User user = (User) session.getAttribute(BaseConfigUtil.USER_SESSION_KEY);

//            被回复的评论
//            Comment lastComment = commentService.findCommentById(commentId).get();
//
            //修改被回复的评论的状态
//            lastComment.setCommentStatus(CommentStatusEnum.PUBLISHED.getCode());
//            commentService.saveByComment(lastComment);

            //保存评论
            Comment comment = new Comment();
//            comment.setPost(post);
//            comment.setCommentAuthor(user.getUserDisplayName());
//            comment.setCommentAuthorEmail(user.getUserEmail());
//            comment.setCommentAuthorUrl(BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_URL.getProp()));
//            comment.setCommentAuthorIp(ServletUtil.getClientIP(request));
//            comment.setCommentAuthorAvatarMd5(SecureUtil.md5(user.getUserEmail()));
//            comment.setCommentDate(DateUtil.date());
//            String lastContent = "<a href='#comment-id-" + lastComment.getCommentId() + "'>@" + lastComment.getCommentAuthor() + "</a> ";
//            comment.setCommentContent(lastContent + OwoUtil.markToImg(HtmlUtil.escape(commentContent)));
//            comment.setCommentAgent(userAgent);
//            comment.setCommentParent(commentId);
//            comment.setCommentStatus(CommentStatusEnum.PUBLISHED.getCode());
//            comment.setIsAdmin(1);
//            commentService.saveByComment(comment);

            //邮件通知
//            new EmailToAuthor(comment, lastComment, post, user, commentContent).start();
        } catch (Exception e) {
            logger.error("回复评论失败：{}", e.getMessage());
        }
        return "redirect:/admin/comments";
    }

    /**
     * 异步发送邮件回复给评论者
     */
    class EmailToAuthor extends Thread {

        private Comment comment;
        private Comment lastComment;
        private Post post;
        private User user;
        private String commentContent;

        private EmailToAuthor(Comment comment, Comment lastComment, Post post, User user, String commentContent) {
            this.comment = comment;
            this.lastComment = lastComment;
            this.post = post;
            this.user = user;
            this.commentContent = commentContent;
        }

        @Override
        public void run() {
//            if (StringUtils.equals(BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.SMTP_EMAIL_ENABLE.getProp()), TrueFalseEnum.TRUE.getDesc()) && StringUtils.equals(BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.COMMENT_REPLY_NOTICE.getProp()), TrueFalseEnum.TRUE.getDesc())) {
//                if (Validator.isEmail(lastComment.getCommentAuthorEmail())) {
//                    Map<String, Object> map = new HashMap<>(8);
//                    map.put("blogTitle", BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_TITLE.getProp()));
//                    map.put("commentAuthor", lastComment.getCommentAuthor());
//                    map.put("pageName", lastComment.getPost().getPostTitle());
//                    if (StringUtils.equals(post.getPostType(), PostTypeEnum.POST_TYPE_POST.getDesc())) {
//                        map.put("pageUrl", BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_URL.getProp()) + "/archives/" + post.getPostUrl() + "#comment-id-" + comment.getCommentId());
//                    } else {
//                        map.put("pageUrl", BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_URL.getProp()) + "/p/" + post.getPostUrl() + "#comment-id-" + comment.getCommentId());
//                    }
//                    map.put("commentContent", lastComment.getCommentContent());
//                    map.put("replyAuthor", user.getUserDisplayName());
//                    map.put("replyContent", commentContent);
//                    map.put("blogUrl", BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_URL.getProp()));
//                    mailService.sendTemplateMail(
//                            lastComment.getCommentAuthorEmail(), "您在" + BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_URL.getProp()) + "的评论有了新回复", map, "common/mail_template/mail_reply.ftl");
//                }
            }
        }

    /**
     * 异步通知评论者审核通过
     */
    class NoticeToAuthor extends Thread {

        private Comment comment;
        private Post post;
        private User user;
        private Integer status;

        private NoticeToAuthor(Comment comment, Post post, User user, Integer status) {
            this.comment = comment;
            this.post = post;
            this.user = user;
            this.status = status;
        }

        @Override
        public void run() {
//            if (StringUtils.equals(BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.SMTP_EMAIL_ENABLE.getProp()), TrueFalseEnum.TRUE.getDesc()) && StringUtils.equals(BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.COMMENT_REPLY_NOTICE.getProp()), TrueFalseEnum.TRUE.getDesc())) {
//                try {
//                    if (status == 1 && Validator.isEmail(comment.getCommentAuthorEmail())) {
//                        Map<String, Object> map = new HashMap<>(6);
//                        if (StringUtils.equals(post.getPostType(), PostTypeEnum.POST_TYPE_POST.getDesc())) {
//                            map.put("pageUrl", BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_URL.getProp()) + "/archives/" + post.getPostUrl() + "#comment-id-" + comment.getCommentId());
//                        } else {
//                            map.put("pageUrl", BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_URL.getProp()) + "/p/" + post.getPostUrl() + "#comment-id-" + comment.getCommentId());
//                        }
//                        map.put("pageName", post.getPostTitle());
//                        map.put("commentContent", comment.getCommentContent());
//                        map.put("blogUrl", BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_URL.getProp()));
//                        map.put("blogTitle", BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_TITLE.getProp()));
//                        map.put("author", user.getUserDisplayName());
//                        mailService.sendTemplateMail(
//                                comment.getCommentAuthorEmail(),
//                                "您在" + BaseConfigUtil.OPTIONS.get(BlogPropertiesEnum.BLOG_URL.getProp()) + "的评论已审核通过！", map, "common/mail_template/mail_passed.ftl");
//                    }
//                } catch (Exception e) {
//                    log.error("邮件服务器未配置：{}", e.getMessage());
//                }
//            }
//        }
     }

    }

}
