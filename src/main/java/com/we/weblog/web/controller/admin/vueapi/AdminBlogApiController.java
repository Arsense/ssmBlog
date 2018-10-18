package com.we.weblog.web.controller.admin.vueapi;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.vue.adminlte4j.model.Menu;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.web.springmvc.ApiAdminController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminBlogApiController extends ApiAdminController {

    public String getUserName(HttpServletRequest request) {
        SSOToken ssoToken = SSOHelper.getSSOToken(request);
        return ssoToken.getIssuer();
    }
    /**
     *
     * @param uiModel
     */
    @Override
    public void configureMenu(UIModel uiModel) {

        //把开发配置放到最后
        List<Menu> menus = new ArrayList<>();

        Menu menu1 = new Menu("1", "仪表盘", "/admin/home.html", "fa fa-dashboard", 1);
        Menu menu2 = new Menu("2", "新随笔", "/admin/add_blog.html", "fa fa-edit", 2);
        Menu menu3 = new Menu("3", "博客管理", "/admin/bloglist.html", "fa fa-edit", 3);
        Menu menu4 = new Menu("4", "评论管理", "/admin/comments.html", "fa fa-weixin", 4);
        Menu menu5 = new Menu("5", "页面管理", "/admin/pages.html", "fa fa-pagelines", 5);
        Menu menu6 = new Menu("6", "分类标签", "/admin/category.html", "fa fa-tags", 6);
        Menu menu7 = new Menu("7", "主题设置", "/admin/scheme.html", "fa fa-laptop", 7);
        Menu menu8 = new Menu("8", "系统设置", "/admin/system.html", "fa fa-cog", 8);
        Menu menu9 = new Menu("9", "退出登录", "/logout.html", "fa fa-sign-out", 9);

        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);
        menus.add(menu6);
        menus.add(menu7);
        menus.add(menu8);
        menus.add(menu9);

        super.configureMenu(uiModel);
        uiModel.menu(menus);
    }

}
