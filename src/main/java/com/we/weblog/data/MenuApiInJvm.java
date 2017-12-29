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

        Menu menu1 = createMenu("/admin/main.html" , "博客首页" , "fa fa-dashboard" , 1) ;
        Menu menu2 = createMenu("/admin/yearblogs.html" , "文章管理" , "fa fa-files-o" , 2) ;
        Menu menu3 = createMenu("/admin/kind.html" , "类别管理" , "fa fa-edit" , 2) ;
        Menu menu4 = createMenu("/admin/message.html" , "评论管理" , "fa fa-share" , 2) ;
        Menu menu5 = createMenu("/admin/bmanage.html" , "博客配置" , "fa fa-cog" , 2) ;

        menu2.addChildMenu(createMenu
                ("/admin/bloglist.html" , "显示所有博客" , "fa fa-circle-o" , 2));

        menu2.addChildMenu(createMenu("/admin/testeditor.html" , "添加新" +
                "" +
                "随笔" , "fa fa-circle-o" , 2));
        menu2.addChildMenu(createMenu("/admin/draft.html" , "草稿箱" , "fa fa-circle-o" , 2));

        menu5.addChildMenu(createMenu("/admin/addblog.html" , "博客签名" , "fa fa-circle-o" , 2));

        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);
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