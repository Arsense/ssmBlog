package com.we.weblog.controller.admin;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.controller.core.BaseController;
import com.we.weblog.domain.util.BaseConfigUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 * @author Clay
 * @date 2018/11/5 19:56
 */
@Controller
@RequestMapping("/admin/theme")
public class ThemeAdminController {

    /**
     * 渲染主题设置页面
     *
     * @param model model
     * @return 模板路径admin/admin_theme
     */
    @GetMapping
    public String themes(Model model) {
        model.addAttribute("activeTheme", BaseController.THEME);
        if (null != BaseConfigUtil.THEMES) {
            model.addAttribute("themes", BaseConfigUtil.THEMES);
        }
        return "admin/admin_theme";
    }

    /**
     * 激活主题
     *
     * @param siteTheme 主题名称
     * @param request   request
     * @return UIModel
     */
    @GetMapping(value = "/set")
    @ResponseBody
    @CacheEvict(value = "posts", allEntries = true, beforeInvocation = true)
    public UIModel activeTheme(@RequestParam("siteTheme") String siteTheme,
                               HttpServletRequest request) {
        return UIModel.success().msg("激活主题成功");
    }


    /**
     * 上传主题
     *
     * @param file 文件
     * @return UIModel
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public UIModel uploadTheme(@RequestParam("file") MultipartFile file,
                                  HttpServletRequest request) {
        return UIModel.success().msg("上传主题成功");

    }

    /**
     * 删除主题
     *
     * @param themeName 主题文件夹名
     * @return string 重定向到/admin/themes
     */
    @GetMapping(value = "/remove")
    public String removeTheme(@RequestParam("themeName") String themeName) {
        return "redirect:/admin/themes";
    }


    /**
     * 在线拉取主题
     *
     * @param remoteAddr 远程地址
     * @param themeName  主题名称
     * @return UIModel
     */
    @PostMapping(value = "/clone")
    @ResponseBody
    public UIModel cloneFromRemote(@RequestParam(value = "remoteAddr") String remoteAddr,
                                      @RequestParam(value = "themeName") String themeName) {
        return UIModel.success().msg("远程拉取主题成功");
    }

    /**
     * 更新主题
     *
     * @param themeName 主题名
     * @return UIModel
     */
    @GetMapping(value = "/pull")
    @ResponseBody
    public UIModel pullFromRemote(@RequestParam(value = "themeName") String themeName) {
        return UIModel.success().msg("更新主题成功");
    }


    /**
     * 跳转到主题设置
     *
     * @param theme theme名称
     */
    @GetMapping(value = "/options")
    public String setting(Model model,
                          @RequestParam("theme") String theme,
                          @RequestParam("hasUpdate") String hasUpdate) {
        return null;
    }


    /**
     * 编辑主题
     *
     * @param model model
     * @return 模板路径admin/admin_theme-editor
     */
    @GetMapping(value = "/editor")
    public String editor(Model model) {

//        List<String> tpls = FileUtil.findAllTemplateFileName(BaseController.THEME);
        return null;
    }



    /**
     * 获取模板文件内容
     *
     * @param tplName 模板文件名
     * @return 模板内容
     */
    @GetMapping(value = "/getTpl", produces = "text/text;charset=UTF-8")
    @ResponseBody
    public String getTplContent(@RequestParam("tplName") String tplName) {
        return null;
    }

    /**
     * 保存修改模板
     *
     * @param tplName    模板名称
     * @param tplContent 模板内容
     * @return UIModel
     */
    @PostMapping(value = "/editor/save")
    @ResponseBody
    public UIModel saveTpl(@RequestParam("tplName") String tplName,
                              @RequestParam("tplContent") String tplContent) {
        return UIModel.success().msg("保存修改模板");

    }
}
