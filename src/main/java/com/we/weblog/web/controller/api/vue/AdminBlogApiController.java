package com.we.weblog.web.controller.api.vue;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.vue.adminlte4j.model.Menu;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.web.springmvc.ApiAdminController;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * 配置不可删改的菜单
 */
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

        Menu menu1 = new Menu("1", "仪表盘"
                , "/admin/admin_index.html", "fa fa-dashboard", 1);
        Menu menu2 = new Menu("2", "文章"
                , "/admin/#", "fa fa-edit", 2);
        menu2.addChildMenu(new Menu("21", "新博客"
                , "/admin/admin_post_add.html", "fa fa-edit", 0));
        menu2.addChildMenu(new Menu("22", "博客列表"
                , "/admin/admin_post.html", "fa fa-edit", 1));
        menu2.addChildMenu(new Menu("23", "标签"
                , "/admin/admin_category.html", "fa fa-tags", 2));

        Menu menu3 = new Menu("4", "评论"
                , "/admin/admin_comment.html", "fa fa-weixin", 3);
        Menu menu4 = new Menu("5", "页面"
                , "/admin/admin_pages.html", "fa fa-pagelines", 4);
        Menu menu5 = new Menu("6", "外观"
                , "/admin/#", "fa fa-paint-brush", 5);

        menu5.addChildMenu(new Menu("51", "主题"
                , "/admin/admin_scheme.html", "fa fa-laptop", 0));
        menu5.addChildMenu(new Menu("52", "主题编辑"
                , "/admin/theme_edit.html", "fa fa-laptop", 1));

        Menu menu6= new Menu("7", "附件"
                , "/admin/admin_scheme.html", "fa fa-sign-out", 9);
        Menu menu7 = new Menu("8", "用户"
                , "/admin/admin_scheme.html", "fa fa-user-o", 7);
        menu7.addChildMenu(new Menu("71", "个人信息"
                , "/admin/admin_scheme.html", "fa fa-user-o", 0));


        Menu menu8 = new Menu("9", "设置"
                , "/admin/admin_system.html", "fa fa-cog", 8);

        menu8.addChildMenu(new Menu("81", "博客设置"
                , "/admin/setting.html", "fa fa-cog", 1));

        menu8.addChildMenu(new Menu("82", "博客备份"
                , "/admin/admin_backup.html", "fa fa-cog", 2));

        Menu menu9 = new Menu("20", "退出登录"
                , "/logout.html", "fa fa-sign-out", 10);

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
