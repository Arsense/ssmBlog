package com.we.weblog.controller.admin.category;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.domain.Category;
import com.we.weblog.domain.modal.Select;
import com.we.weblog.service.CategoryService;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  * <pre>
 *     公共常量
 * </pre>
 * 分类标签控制器
 * created by Clay at 2018/3/19
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {

    @Resource
    private PostService postService;
    @Resource
    private TagService tagService;
    @Resource
    private CategoryService categoryService;

    /**
     *  添加博客分类显示
     * @return
     */
    @GetMapping("/index")
    @ResponseBody
    public List<Select> getAllKindCategories(){
//        {code:'1' ,  label:'男' ,checked:false},
//        return  tagService.getCategories();
        return null;
    }
    /**
     * 删除类别
     */
    @GetMapping("/delete/{name}")
    @ResponseBody
    public UIModel deleteCategory(@PathVariable("name") String categoryName){

        if (StringUtils.isEmpty(categoryName)) {
            return UIModel.fail().msg("删除类别异常");
        }
        try {
            postService.removePostCategory(categoryName);
        } catch (Exception e) {
            return UIModel.fail().msg("删除失败");
        }
        return UIModel.success().msg("删除成功");

    }

    /**
     * 分类标签管理页面
     */
    @GetMapping("/manage")
    @ResponseBody
    public Map<String,Object> manageCategoryAndTag() {
        Map<String,Object> maps  = new HashMap<>();
        maps.put("categories", tagService.getMates());
        maps.put("tags",tagService.findAllTags());
        return maps;
    }

    /**
     * 保存新分类
     * @return
     */
    @GetMapping("/save/{name}")
    @ResponseBody
    public UIModel newCategory(@PathVariable("name")  String name) {
//        //这里全是空格 全是数字 null 都要检查
//        if (StringUtils.isEmpty(name)) {
//            return UIModel.fail().msg("请输入类别");
//        } else if(name.length()> 25) {
//            return  UIModel.fail().msg("您输入的类别过长");
//        }
//        List<String> tagName = tagService.getMates();
//        if (tagName.contains(name)) {
//            return UIModel.fail().msg("该分类已存在");
//        }
//        try {
//            tagService.saveCategory(name);
//        } catch (Exception e) {
//            return UIModel.fail().msg("添加失败");
////            log.error("修改分类失败：{}", e.getMessage());
//        }
//

        return UIModel.success().msg("添加成功");

    }

    /**
     * 跳转到修改页面
     *
     * @param cateId cateId
     * @param model  model
     * @return 模板路径admin/admin_category
     */
    @GetMapping(value = "/edit")
    public String toEditCategory(Model model, @RequestParam("cateId") Integer cateId) {
//        Category category = categoryService.findByCateId(cateId);
//        model.addAttribute("updateCategory", category);
        return "admin/admin_category";
    }

}
