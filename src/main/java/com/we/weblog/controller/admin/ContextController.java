package com.we.weblog.controller.admin;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.controller.BaseController;
import com.we.weblog.domain.Context;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.modal.LogActions;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.service.ContextService;
import com.we.weblog.service.LogService;
import com.we.weblog.service.TagService;
import com.we.weblog.tool.IpTool;
import org.bouncycastle.jcajce.provider.asymmetric.util.DSABase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private LogService logService;
    private TagService tagService;
    private  int updateId = 0;

    @Autowired
    public ContextController(ContextService contextService,LogService  logService,TagService tagService){
        this.tagService = tagService;
        this.contextService = contextService;
        this.logService = logService;
    }


    @GetMapping("/delete/{id}")
    @ResponseBody
    public UIModel deleteBlog(@PathVariable("id") int deleteId, HttpServletRequest request) {
        if(deleteId <= 0){
            return UIModel.fail().setMsg("该博客不存在");
        }
        contextService.deleteBlogById(deleteId);
        tagService.deleteTag(deleteId);

        logService.addLog(new Log(LogActions.DELETE_BLOG,deleteId+" ", IpTool.getIpAddress(request),1));

        return UIModel.success().setMsg("删除成功");

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

     * @return
     */
    @PostMapping("/send")
    @ResponseBody
    public UIModel postAction(@RequestBody Context context) throws Exception {
        Boolean inputCheck = false;
        String  messgae = null;
        if(context.getTitle().equals("")){
            messgae = "博客标题不能为空";
        }else if(context.getTags().equals("")){
            messgae = "博客标签不能为空";
        }else if(context.getArticle().equals("")){
            messgae = "请输入博客的内容";
        }else{
            inputCheck = true;
        }

        if(!inputCheck){
            return UIModel.fail().setMsg(messgae);
        }

        context.setType(Types.ARTICLE);
        contextService.addBlog(context);
        Log loginLog =new Log(LogActions.ADD_BLOG,"admin", IpTool.getIpAddress(request),1);
        if(logService.addLog(loginLog)<0){
           messgae = "添加博客失败";
        }


        messgae = "添加博客成功！";
        return UIModel.success().setMsg(messgae);
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
