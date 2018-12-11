package com.we.weblog.web.controller.admin;

import cn.hutool.core.date.DateUtil;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.vue.adminlte4j.web.config.BaseConfig;
import com.we.weblog.domain.User;
import com.we.weblog.domain.util.BaseConfigUtil;
import com.we.weblog.service.*;
import com.we.weblog.web.controller.core.BaseController;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.modal.Picture;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.domain.util.AddressUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 *
 *  * <pre>
 *     公共常量
 * </pre>
 * 文章控制管理器
 * created by clay
 */
@Controller
@RequestMapping("/admin")
public class PostAdminController extends BaseController{

    @Resource
    private PostService postService;

    @Resource
    private CommentService commentSerivce;

    @Resource
    private LogsService logService;

    @Resource
    private TagService tagService;

    @Resource
    private FileService fileService;

    private int updateId = 0;


    /**
     * 删除文章
     * @param deleteId
     * @param request
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public UIModel deleteBlog(@PathVariable("id") int deleteId, HttpServletRequest request) {
        if (deleteId <= 0) {
            return UIModel.fail().msg("删除文章非法");
        }
        Post context = postService.findByPostId(deleteId);
        if(StringUtils.isEmpty(context)) {
            return UIModel.fail().msg("该博客不存在");
        }
        postService.removeByPostId(deleteId);
        tagService.deleteTag(deleteId);

        commentSerivce.removeByCommentId(deleteId);
        logService.saveByLogs(new Log(LogActions.DELETE_BLOG,deleteId+" ", AddressUtil.getIpAddress(request),1));

        return UIModel.success().msg("删除成功");

    }

    /**
     * 去除html，htm后缀，以及将空格替换成-
     *
     * @param url url
     * @return String
     */
    private static String urlFilter(String url) {
        if (null != url) {
            final boolean urlEndsWithHtmlPostFix = url.endsWith(".html") || url.endsWith(".htm");
            if (urlEndsWithHtmlPostFix) {
                return url.substring(0, url.lastIndexOf("."));
            }
        }
        return org.apache.commons.lang3.StringUtils.replaceAll(url, " ", "-");
    }

    /**
     * 更新文章
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public Picture uploadPickture(HttpServletRequest request) throws Exception {
        return  fileService.loadPicture(request);
    }


    /**
     * 返回需要修改的博客
      * @return
     */
     @GetMapping("/update_send_data/{id}")
     @ResponseBody
     public Map<String, Object> getTagretUpdateContext(@PathVariable int id){
         updateId = id;
         Map<String,Object> maps = new HashMap<>();
         Post context = postService.findByPostId(updateId);

         maps.put("context",context);
         maps.put("options",tagService.getCategories());

         return maps;
     }

    /**
     * 修改博客
     * @param context
     * @return
     * @throws SQLException
     * @throws IOException
     */
     @PostMapping("/update")
     @ResponseBody
     public UIModel updateDate(@RequestBody Post context) throws SQLException {
        postService.updatePost(context,updateId);
         return UIModel.success().msg("修改成功！");
     }

    /**
     * 发布文章
     * @return
     */
    @PostMapping("/send")
    @ResponseBody
    public UIModel postAction(@RequestBody Post context) throws Exception {
        String message = validateContext(context);

        if (!StringUtils.isEmpty(message))
            return UIModel.fail().msg(message);

        context.setType(Types.ARTICLE);
        postService.saveByPost(context);
        Log loginLog =new Log(LogActions.ADD_BLOG,"admin", AddressUtil.getIpAddress(request),1);
        if (logService.saveByLogs(loginLog) < 0) {
            message = "添加博客失败";
        }
        message = "添加博客成功！";
        return UIModel.success().msg(message);
    }




    /**
     * 显示后台博客列表
     * @return
     */
    @GetMapping("/blog/list")
    @ResponseBody
    public UIModel getBlogList() {
        List<Post> tempContexts = postService.findLastestPost(1);

        FormModel formModel = new FormModel();
        formModel.createFormItem("uid").setHidden(false).setLabel("博客编号");
        formModel.createFormItem("title").setHidden(false).setLabel("标题");
        formModel.createFormItem("tags").setHidden(false).setLabel("标签");
        formModel.createFormItem("hits").setHidden(false).setLabel("访问量");
        formModel.createFormItem("month").setHidden(false).setLabel("发布时间");

        TableData tableData = new TableData() ;
        tableData.setFormItems(formModel.getFormItems());
        tableData.setDataItems(tempContexts);
        tableData.setTotalSize(commentSerivce.getCommentCount());

        return  UIModel.success().tableData(tableData);

    }


    /**
     * 模糊查询文章
     *
     * @param model   Model
     * @param keyword keyword 关键字
     * @param page    page 当前页码
     * @param size    size 每页显示条数
     * @return 模板路径admin/admin_post
     */
    @PostMapping(value = "/search")
    public String searchPost(Model model,
                             @RequestParam(value = "keyword") String keyword,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        try {
            //排序规则
            model.addAttribute("posts", postService.searchPosts(keyword));
        } catch (Exception e) {
            logger.error("未知错误：{}", e.getMessage());
        }
        return "admin/admin_post";
    }

    /**
     * 处理预览文章的请求
     *
     * @param postId 文章编号
     * @param model  model
     * @return 模板路径/themes/{theme}/post
     */
    @GetMapping(value = "/view")
    public String viewPost(@RequestParam("postId") int postId, Model model) {
        Post post = postService.findByPostId(postId);
        model.addAttribute("post", post);
        return this.redirectTo("post");
    }

    /**
     * 自动保存文章为草稿
     *
     * @param postId        文章编号
     * @param postTitle     文章标题
     * @param postUrl       文章路径
     * @param postContentMd 文章内容
     * @param postType      文章类型
     * @param session       session
     * @return UIModel
     */
    @PostMapping(value = "/new/autoPush")
    @ResponseBody
    public UIModel autoPushPost(@RequestParam(value = "postId", defaultValue = "0") int postId,
                                   @RequestParam(value = "postTitle") String postTitle,
                                   @RequestParam(value = "postUrl") String postUrl,
                                   @RequestParam(value = "postContentMd") String postContentMd,
                                   @RequestParam(value = "postType", defaultValue = "post") String postType,
                                   HttpSession session) {
        Post post = null;
        User user = (User) session.getAttribute(BaseConfigUtil.USER_SESSION_KEY);
        if (postId == 0) {
            post = new Post();
        } else {
            post = postService.findByPostId(postId);
        }
        try {
            if (org.apache.commons.lang3.StringUtils.isEmpty(postTitle)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//                post.setPostTitle("草稿：" + dateFormat.format(DateUtil.date()));
            } else {
//                post.setPostTitle(postTitle);
            }
            if (org.apache.commons.lang3.StringUtils.isEmpty(postUrl)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//                post.setPostUrl(dateFormat.format(DateUtil.date()));
            } else {
//                post.setPostUrl(postUrl);
            }
//            post.setPostId(postId);
//            post.setPostStatus(1);
//            post.setPostContentMd(postContentMd);
//            post.setPostType(postType);
//            post.setPostDate(DateUtil.date());
//            post.setPostUpdate(DateUtil.date());
//            post.setUser(user);
        } catch (Exception e) {
            logger.error("未知错误：{}", e.getMessage());
            return UIModel.fail().msg("保存失败");
        }
        return UIModel.success().msg("保存成功");

    }


    /**
     * 处理移至回收站的请求
     *
     * @param postId 文章编号
     * @return 重定向到/admin/posts
     */
    @GetMapping(value = "/throw")
    public String moveToTrash(@RequestParam("postId") Long postId, @RequestParam("status") Integer status) {
        try {
            postService.updatePostStatus(postId, 1);
            logger.info("编号为" + postId + "的文章已被移到回收站");
        } catch (Exception e) {
            logger.error("删除文章到回收站失败：{}", e.getMessage());
        }
        return "redirect:/admin/posts?status=" + status;
    }

    /**
     * 处理文章为发布的状态
     *
     * @param postId 文章编号
     * @return 重定向到/admin/posts
     */
    @GetMapping(value = "/revert")
    public String moveToPublish(@RequestParam("postId") Long postId,
                                @RequestParam("status") Integer status) {
        try {
            postService.updatePostStatus(postId, 0);
            logger.info("编号为" + postId + "的文章已改变为发布状态");
        } catch (Exception e) {
            logger.error("发布文章失败：{}", e.getMessage());
        }
        return "redirect:/admin/posts?status=" + status;
    }




    private String validateContext(Post context) {
        String messgae = null;
        if (StringUtils.isEmpty(context.getTitle())) {
            messgae = "博客标题不能为空";
        } else if (context.getTags().equals("")) {
            messgae = "博客标签不能为空";
        } else if (context.getArticle().equals("")) {
            messgae = "请输入博客的内容";
        } else if (context.getCategories().equals("")) {
            messgae = "未选择博客分类";
        } else if (context.getArticle().length() < 10) {
            messgae = "请输入长度为5的内容";
        }
        int length = context.article.length();
        if (length > 20000) {
            throw new RuntimeException("文章内容最多可以输入" + 20000 + "个字符");
        }

        SSOToken ssoToken = SSOHelper.getSSOToken(request);
        if(ssoToken == null) {
            throw new RuntimeException("请登录后发布文章");
        }
        return messgae;
    }




}
