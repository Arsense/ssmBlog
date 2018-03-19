package com.we.weblog.controller.admin;


import com.we.weblog.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {


    private ContextService contextService;

    @Autowired
    public CategoryController(ContextService contextService){
        this.contextService = contextService;
    }



    @GetMapping("/")
    @ResponseBody
    public List<String> getAllKindCategories(){
        return  contextService.getCategories();
    }


    /**
     * 分类标签管理页面
     * @return
     */
    @GetMapping("/manage")
    @ResponseBody
    public Map<String,Object> manageCategoryAndTag(){


        Map<String,Object> maps  = new HashMap<>();

        List<String> cates = contextService.getCategories();
        List<String> tags = contextService.getAllKindTags();

        maps.put("categories",cates);
        maps.put("tags",tags);

        return maps;



    }

}
