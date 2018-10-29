package com.we.weblog.web.controller.admin;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.service.*;
import com.we.weblog.web.controller.BaseController;
import com.we.weblog.domain.Comment;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.UploadPicture;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.tool.IpTool;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文章控制管理器
 * created by clay
 */
@Controller
@RequestMapping("/admin")
public class ArticleController extends BaseController{

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
        logService.saveByLogs(new Log(LogActions.DELETE_BLOG,deleteId+" ", IpTool.getIpAddress(request),1));

        return UIModel.success().msg("删除成功");

    }


    /**
     * 更新文章
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public UploadPicture uploadPickture(HttpServletRequest request) throws Exception {
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
        Log loginLog =new Log(LogActions.ADD_BLOG,"admin", IpTool.getIpAddress(request),1);
        if (logService.saveByLogs(loginLog) < 0) {
            message = "添加博客失败";
        }
        message = "添加博客成功！";
        return UIModel.success().msg(message);
    }


    /**
     *  前端仪表盘
     * @param request
     * @return
     */
    @GetMapping("/index/data")
    @ResponseBody
    public Map<String,Object> index(HttpServletRequest request){

        //获得最新的20条日志  获得最新的文章  后台统计对象
        Map<String,Object> map = new HashMap<>();
        int blogCount = postService.findPostCount();
        int commnetCount = commentSerivce.getCommentCount();
        List<Post> contexts = postService.findLastestPost(5);
        List<Comment> comments = commentSerivce.getAllComments();
        List<Log> logs = logService.findLastestTenLogs(10);

        map.put("blogNumber",blogCount);
        map.put("contexts", contexts);
        map.put("logs",logs);
        map.put("comments",comments);
        map.put("commentNumber",commnetCount);

        return map;
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
