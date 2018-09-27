package com.we.weblog.controller.admin;

import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.controller.BaseController;
import com.we.weblog.domain.Comment;
import com.we.weblog.domain.Context;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.UploadPicture;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.service.*;
import com.we.weblog.tool.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class ContextController extends BaseController{

    private ContextService contextService;
    private CommentSerivce commentSerivce;
    private LogService logService;
    private TagService tagService;
    private  int updateId = 0;
    private FileService fileService;

    @Autowired
    public ContextController(ContextService contextService,LogService  logService,TagService tagService,CommentSerivce commentSerivce,FileService fileService){
        this.commentSerivce = commentSerivce;
        this.tagService = tagService;
        this.contextService = contextService;
        this.logService = logService;
        this.fileService = fileService;
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public UIModel deleteBlog(@PathVariable("id") int deleteId, HttpServletRequest request) {
        //todo 删除文章 相关的评论什么的都得删除
        if (deleteId <= 0) {
            return UIModel.fail().msg("该博客不存在");
        }

        contextService.deleteBlogById(deleteId);
        tagService.deleteTag(deleteId);
        logService.addLog(new Log(LogActions.DELETE_BLOG,deleteId+" ", IpTool.getIpAddress(request),1));

        return UIModel.success().msg("删除成功");

    }


    @PostMapping("/upload")
    @ResponseBody
    public UploadPicture uploadPickture(HttpServletRequest request) throws Exception {
        UploadPicture picture =  fileService.loadPicture(request);
        return picture;
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
         Context context = contextService.getBlogById(updateId);

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
     public UIModel updateDate(@RequestBody Context context) throws SQLException {
        contextService.updateBlog(context,updateId);
         return UIModel.success().msg("修改成功！");

     }

    /**
     * 添加博客的表单控制器

     * @return
     */
    @PostMapping("/send")
    @ResponseBody
    public UIModel postAction(@RequestBody Context context) throws Exception {
        String messgae = validateContext(context);

        if (messgae.length() > 0)
            return UIModel.fail().msg(messgae);

        context.setType(Types.ARTICLE);
        contextService.addBlog(context);
        Log loginLog =new Log(LogActions.ADD_BLOG,"admin", IpTool.getIpAddress(request),1);
        if (logService.addLog(loginLog)<0) {
           messgae = "添加博客失败";
        }
        messgae = "添加博客成功！";
        return UIModel.success().msg(messgae);
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
        int blogCount = contextService.getTotalBlog();
        int commnetCount = commentSerivce.getCounts();
        List<Context> contexts = contextService.getRecentBlogs(5);
        List<Comment> comments = commentSerivce.getComments();
        List<Log> logs = logService.getLogPages(10);

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
        List<Context> tempContexts=contextService.showBlogs(1);

        FormModel formModel = new FormModel();
        formModel.createFormItem("uid").setHidden(false).setLabel("博客编号");
        formModel.createFormItem("title").setHidden(false).setLabel("标题");
        formModel.createFormItem("tags").setHidden(false).setLabel("标签");
        formModel.createFormItem("hits").setHidden(false).setLabel("访问量");
        formModel.createFormItem("month").setHidden(false).setLabel("发布时间");

        TableData tableData = new TableData() ;
        tableData.setFormItems(formModel.getFormItems());
        tableData.setDataItems(tempContexts);
        tableData.setTotalSize(commentSerivce.getCounts());

        return  UIModel.success().tableData(tableData);

    }

    private String validateContext(Context context) {
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
        return messgae;
    }




}
