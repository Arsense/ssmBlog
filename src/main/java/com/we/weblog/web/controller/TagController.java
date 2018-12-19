package com.we.weblog.web.controller;

import com.we.weblog.domain.Post;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import com.we.weblog.web.controller.core.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import java.util.List;

/**
 * <pre>
 *     标签归档控制器
 * </pre>
 * @author tangwei
 * @date 2018/10/30 11:04
 */

@Controller
public class TagController extends BaseController{


    @Resource
    private PostService postService;
    /**
     * 首页视图
     * @return
     */
    @GetMapping("/tags")
    public String tags(){
        return redirectTo("/tags");
    }

    @GetMapping("/tag/{tageName}")
    public String findArticleByTag(@PathVariable("tageName")String tagName){
        List<Post> posts = postService.findByTagName(tagName);

        return redirectTo("/tags");
    }


}
