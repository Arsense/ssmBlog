package com.we.weblog.controller.admin;


import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类标签控制器
 * created by tangwei at 2018/3/19
 */
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

    @GetMapping("/delete/{name}")
    @ResponseBody
    public UIModel deleteCategory(@PathVariable("name") String categoryName){

          if(categoryName.equals("")){
            return UIModel.fail().setMsg("删除的类别为空");
          }
          int result = contextService.deleteCatories(categoryName);
          if(result >=0){
              return UIModel.success().setMsg("删除成功");
          }else{
              return UIModel.fail().setMsg("删除失败");
          }
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


    /**
     * 保存新分类
     * @return
     */
    @GetMapping("/save")
    @ResponseBody
    public void newCategory(){



    }

}
