package com.we.weblog.web.controller;

import com.we.weblog.web.controller.core.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <pre>
 *     标签归档控制器
 * </pre>
 * @author tangwei
 * @date 2018/10/30 11:04
 */

@Controller
public class TagController extends BaseController{
    /**
     * 首页视图
     * @return
     */
    @GetMapping("/tags")
    public String tags(){
        return redirectTo("/tags");
    }

}
