package com.we.weblog.controller.admin;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.controller.BaseController;
import com.we.weblog.domain.Context;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.CategoriesBlog;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.service.ContextService;
import com.we.weblog.service.LogService;
import com.we.weblog.service.TagService;
import com.we.weblog.tool.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class ContextController extends BaseController{

    private ContextService contextService;
    private LogService logService;
    private TagService tagService;
    private  int updateId = 0;

    @Autowired
    public ContextController(ContextService contextService,LogService  logService,TagService tagService){
        this.tagService = tagService;
        this.contextService = contextService;
        this.logService = logService;
    }


    @GetMapping("/delete")
    public  void deleteBlog(@RequestParam int deleteId,HttpServletRequest request) {

        int id = deleteId;
        contextService.deleteBlogById(id);
        tagService.deleteTag(id);


        logService.addLog(new Log(LogActions.DELETE_BLOG,id+" ", IpTool.getIpAddress(request),1));

    }



    /**
     * 返回需要修改的博客
      * @return
     */
     @GetMapping("/update_send_data/{id}")
     @ResponseBody
     public Context getTagretUpdateContext(@PathVariable int id){
        updateId = id;
         return contextService.getBlogById(updateId);
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

         return UIModel.success().setMsg("修改成功！");
     }


    /**
     * 添加博客的表单控制器
     * @param context 表单中提交的博客信息,包括标题，标签，md页面，和md转成的html页面
     * @return
     */
    @PostMapping("/send")
    public void postAction(@ModelAttribute("blogFrom")Context context, HttpServletResponse response) throws Exception {

           context.setType(Types.ARTICLE);
           contextService.addBlog(context);

        Log loginLog =new Log(LogActions.ADD_BLOG,"admin", IpTool.getIpAddress(request),1);
        if(logService.addLog(loginLog)<0){
            throw new Exception("添加博客失败");
        }
           response.sendRedirect("/admin/show.html");

    }


    /**
     *  仪表盘
     * @param request
     * @return
     */
    @GetMapping("/index_data")
    @ResponseBody
    public Map<String,Object> index(HttpServletRequest request){
        //获得最新的20条日志  获得最新的文章  后台统计对象
        Map<String,Object> map = new HashMap<>();
        List<Context> contexts = contextService.getRecentBlogs(5);
        int blogCount = contextService.getTotalBlog();


        List<Log> logs = logService.getLogPages(10);
        map.put("blogNumber",blogCount);
        map.put("contexts", contexts);
        map.put("logs",logs);

        return map;
    }



}
