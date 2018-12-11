package com.we.weblog.web.controller.admin;

import com.we.weblog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 *     后台设置选项控制器
 * </pre>
 * @author tangwei
 * @date 2018/11/5 19:56
 */
@Controller
@RequestMapping("/admin/option")
public class OptionAdminController {
    @Autowired
    private OptionsService optionsService;
    /**
     * 请求跳转到option页面并完成渲染
     *
     * @return 模板路径admin/admin_option
     */
    @GetMapping
    public String options() {
        return "admin/admin_option";
    }


}
