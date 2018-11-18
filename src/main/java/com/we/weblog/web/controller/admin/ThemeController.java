package com.we.weblog.web.controller.admin;

import com.we.weblog.web.controller.core.BaseController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tangwei
 * @date 2018/11/5 19:56
 */
@RequestMapping("/admin/theme")
public class ThemeController {

    /**
     * 渲染主题设置页面
     *
     * @param model model
     * @return 模板路径admin/admin_theme
     */
    @GetMapping
    public String themes(Model model) {
        model.addAttribute("activeTheme", BaseController.THEME);
//        if (null != BaseBlogEnum.THEMES) {
//            model.addAttribute("themes", HaloConst.THEMES);
//        }
        return "admin/admin_theme";
    }
}
