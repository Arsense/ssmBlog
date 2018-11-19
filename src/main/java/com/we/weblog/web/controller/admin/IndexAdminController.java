package com.we.weblog.web.controller.admin;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.domain.Comment;
import com.we.weblog.domain.Log;
import com.we.weblog.domain.Post;
import com.we.weblog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * <pre>
 *     * 后端主要管理页面  日志清理和index显示内容

 * </pre>
 *
 * @author tangwei
 * @date 2018/11/5 19:57
 */
@Controller
@RequestMapping("/admin")

public class IndexAdminController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexAdminController.class);

    @Resource
    private LogsService logsService;
    @Resource
    private PostService postService;
    @Resource
    private CommentService commentSerivce;
    @Resource
    private TagService tagService;
    @Resource
    private FileService fileService;

    /**
     *  前端仪表盘
     * @return
     */
    @GetMapping("/index/data")
    @ResponseBody
    public Map<String,Object> index(){
        //获得最新的20条日志  获得最新的文章  后台统计对象
        Map<String,Object> map = new HashMap<>();

        //文章总数 TODO 其实Long会更好
        int blogCount = postService.findPostCount();
        //评论总数
        int commnetCount = commentSerivce.getCommentCount();
        //查询最新的文章
        List<Post> contexts = postService.findLastestPost(5);

        //查询最新的评论
        List<Comment> comments = commentSerivce.getAllComments();

        //查询最新的日志
        List<Log> logs = logsService.findLastestTenLogs(10);

        map.put("blogNumber",blogCount);
        map.put("contexts", contexts);
        map.put("logs",logs);
        map.put("comments",comments);
        map.put("commentNumber",commnetCount);
        return map;
    }

    /**
     * 清除所有日志
     *
     * @return
     */
    @GetMapping(value = "/logs/clear")
    public UIModel logsClear() {
        try {
            logsService.removeAllLogs();
        } catch (Exception e) {
            LOG.error("清除日志失败：{}" + e.getMessage());
            return UIModel.fail().msg("日志清理成功");
        }
        return UIModel.success().msg("日志清理成功");
    }
}
