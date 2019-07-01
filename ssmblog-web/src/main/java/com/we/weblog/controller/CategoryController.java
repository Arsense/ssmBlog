package com.we.weblog.controller;

import com.we.weblog.controller.core.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <pre>
 *     前台文章分类控制器
 * </pre>
 *
 * @author Clay
 * @date 2018/10/30 11:06
 */

@Controller
public class CategoryController extends BaseController {

    /**
     * 首页视图
     * @return
     */
    @GetMapping("/category")
    public String category(){
        return redirectTo("/category");
    }

}
