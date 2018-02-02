package com.we.weblog.controller.admin;



import com.we.weblog.domain.Context;
import com.we.weblog.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class ContextController {

    private ContextService contextService;
    private int setUpdateId = 0;

    @Autowired
    public ContextController(ContextService contextService){
        this.contextService = contextService;
    }


    @GetMapping("/delete")
    public  void deleteBlog(@RequestParam int deleteId,HttpServletResponse response) throws IOException {
        int id = deleteId;
        contextService.deleteBlogById(id);
        //response.sendRedirect("/admin/show.html");
    }

    @GetMapping("/toupdate")
    public  void updateBlog(@RequestParam int updateId) throws IOException {
        setUpdateId = updateId;
        //查找博客 md显示 标签还要处理吗？
    }

     @GetMapping("/update_send_data")
     @ResponseBody
     public Context updateData(){
         return contextService.getBlogById(setUpdateId);

     }
    /**
     * 添加博客的表单控制器
     * @param context 表单中提交的博客信息,包括标题，标签，md页面，和md转成的html页面
     * @return
     */

    @PostMapping("/send")
    public void postAction(@ModelAttribute("blogFrom")Context context, HttpServletResponse response) throws IOException {
             contextService.addBlog(context);
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

        map.put("blogNumber",blogCount);
        map.put("contexts", contexts);

        return map;
    }


}
