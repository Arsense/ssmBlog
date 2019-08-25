package com.we.weblog.controller.admin.tag;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.controller.core.BaseController;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * <pre>
 *     公共常量
 * </pre>
 */
@Controller
@RequestMapping("/admin/tags")
public class TagAdminController extends BaseController {

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
        int result = 0;
        int check = 0;
        try {
//            result = tagService.clearTagData(tagName);
//            check = postService.removePostCategory(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result >= 0 && check >= 0) {
            return UIModel.success().msg("删除成功");
        } else {
            return UIModel.fail().msg("删除失败");
        }
    }

//
//    /**
//     * 新增/修改标签
//     *
//     * @param tag tag
//     * @return 重定向到/admin/tag
//     */
//    @PostMapping(value = "/save")
//    public String saveTag(@ModelAttribute Tags tag) {
//        try {
//            tagService.saveByTag(tag);
//        } catch (Exception e) {
//            log.error("新增/修改标签失败：{}", e.getMessage());
//        }
//        return "redirect:/admin/tag";
//    }
//
//    /**
//     * 验证是否存在该路径
//     *
//     * @param tagUrl 标签路径名
//     * @return true：不存在，false：已存在
//     */
//    @GetMapping(value = "/checkUrl")
//    @ResponseBody
//    public UIModel checkTagUrlExists(@RequestParam("tagUrl") String tagUrl) {
//        Tags tag = tagService.findByTagUrl(tagUrl);
//        if (null != tag) {
//            return new UIModel(ResultCodeEnum.FAIL.getCode(), localeMessageUtil.getMessage("code.admin.result.url-is-exists"));
//        }
//        return new UIModel(ResultCodeEnum.SUCCESS.getCode(), "");
//    }
//
//    /**
//     * 处理删除标签的请求
//     *
//     * @param tagId 标签编号
//     * @return 重定向到/admin/tag
//     */
//    @GetMapping(value = "/remove")
//    public String removeTag(@RequestParam("tagId") Long tagId) {
//        try {
//            tagService.removeByTagId(tagId);
//        } catch (Exception e) {
//            log.error("删除标签失败：{}", e.getMessage());
//        }
//        return "redirect:/admin/tag";
//    }
//
//    /**
//     * 跳转到修改标签页面
//     *
//     * @param model model
//     * @param tagId 标签编号
//     * @return 模板路径admin/admin_tag
//     */
//    @GetMapping(value = "/edit")
//    public String toEditTag(Model model, @RequestParam("tagId") Long tagId) {
//        Tags tag = tagService.findByTagId(tagId).get();
//        model.addAttribute("updateTag", tag);
//        return "admin/admin_tag";
//    }
}
