package com.we.weblog.controller.admin;


import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.controller.BaseController;
import com.we.weblog.service.ContextService;
import com.we.weblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/tags")
public class TagController extends BaseController {

    private ContextService contextService;
    private TagService tagService;

    @Autowired
    public TagController(ContextService contextService,TagService tagService){
        this.contextService = contextService;
        this.tagService = tagService;
    }

    @GetMapping("/delete/{name}")
    @ResponseBody
    public UIModel deleteTags(@PathVariable("name") String tagName){
        if (tagName.equals("")) {
            return UIModel.fail().setMsg("删除的标签类别为空");
        }
        int result = tagService.clearTagData(tagName);
        int check = contextService.deleteCatories(tagName);
        if(result >=0 && check >=0){
            return UIModel.success().setMsg("删除成功");
        } else {
            return UIModel.fail().setMsg("删除失败");
        }
    }
}
