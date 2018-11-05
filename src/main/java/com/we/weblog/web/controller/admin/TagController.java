package com.we.weblog.web.controller.admin;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import com.we.weblog.web.controller.core.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/tags")
public class TagController extends BaseController {

    @Resource
    private PostService postService;
    @Resource
    private TagService tagService;


    @GetMapping("/delete/{name}")
    @ResponseBody
    public UIModel deleteTags(@PathVariable("name") String tagName) {
        if (tagName.equals("")) {
            return UIModel.fail().msg("删除的标签类别为空");
        }
        int result = tagService.clearTagData(tagName);
        int check = postService.removePostCategory(tagName);
        if (result >= 0 && check >= 0) {
            return UIModel.success().msg("删除成功");
        } else {
            return UIModel.fail().msg("删除失败");
        }
    }
}
