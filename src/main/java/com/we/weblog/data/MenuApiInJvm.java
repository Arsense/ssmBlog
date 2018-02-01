package com.we.weblog.data;

import com.we.weblog.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by bjliuyong on 2017/12/14.
 */
public class MenuApiInJvm {

    public static List<Menu> getMenu() {

        List<Menu> menus = new ArrayList<>() ;

        Menu menu1 = createMenu("/admin/index.html" , "仪表盘" , "fa fa-dashboard" , 1) ;
        Menu menu2 = createMenu("/admin/testeditor.html" , "新随笔" , "fa fa-edit" , 2) ;
        Menu menu3 = createMenu("/admin/show.html" , "博客管理" , "fa fa-edit" , 2) ;
        Menu menu4 = createMenu("/admin/message.html" , "评论管理" , "fa  fa-weixin" , 2) ;
        Menu menu5 = createMenu("/admin/tag_manage.html" , "页面管理" , "fa  fa-pagelines" , 2) ;
        Menu menu6 = createMenu("/admin/bmanage.html" , "分类标签" , "fa fa-tags" , 2) ;
        Menu menu7 = createMenu("/admin/scheme.html" , "主题设置" , "fa fa fa-laptop" , 2) ;
        Menu menu8 = createMenu("/admin/system.html" , "系统设置" , "fa fa-cog" , 2) ;
        Menu menu9 = createMenu("/logout.html" , "退出登录" , "fa fa-sign-out" , 2) ;

        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);
        menus.add(menu6);
        menus.add(menu7);
        menus.add(menu8);
        menus.add(menu9);

        return menus;
    }

    private static Menu createMenu(String url , String desc , String icon ,int order) {
        Menu menu = new Menu() ;
        menu.setId(UUID.randomUUID().toString());
        menu.setUrl(url);
        menu.setDesc(desc);
        menu.setIcon(icon);
        menu.setOrder(order);
        return menu ;
    }
}