package com.we.weblog.web.controller.core;


import com.we.weblog.domain.enums.PropertyEnum;
import com.we.weblog.service.OptionsService;
import com.we.weblog.domain.util.BaseConfigUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 *  <pre>
 *     博客初始化控制器
 * </pre>
 *  安装页面留用
 * @date 2019-03-28 13:50
 * @author tangwei9
 */
@Controller
@RequestMapping(value = "/install")
public class InstallController {

    //TODO 博客安装页面进行中
    private static final Logger LOG = LoggerFactory.getLogger(InstallController.class);

    @Resource
    private OptionsService optionsService;

    /**
     * 渲染安装页面
     *
     * @return 模板路径
     */
    @GetMapping
    public String install(Model model) throws Exception {
//        try {
//            if (StringUtils.equals("true", BaseConfigUtil.OPTIONS.get(PropertyEnum.IS_INSTALL))) {
//                //TODO 如果安装前端显示true的部分
//                model.addAttribute("isInstall", true);
//            } else {
//                model.addAttribute("isInstall", true);
//            }
//        } catch (Exception e) {
//            LOG.error(e.getMessage());
//            throw new Exception("安装失败");
//        }
        return "common/install";
    }

    /**
     * 执行安装
     *
     * @param blogTitle       博客标题
     * @param blogUrl         博客网址
     * @param userName        用户名
     * @param userDisplayName 用户名显示名
     * @param email           用户邮箱
     * @param password        用户密码
     * @param request         request
     * @return true：安装成功，false：安装失败
     */
    @PostMapping(value = "/do")
    @ResponseBody
    public boolean doInstall(@RequestParam("blogTitle") String blogTitle,
                             @RequestParam("blogUrl") String blogUrl,
                             @RequestParam("userName") String userName,
                             @RequestParam("userDisplayName")
                                         String userDisplayName,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpServletRequest request){

        if (StringUtils.equals("true", BaseConfigUtil.OPTIONS.get(PropertyEnum.IS_INSTALL.getProp()))) {
            return false;
        }

        optionsService.saveOption(PropertyEnum.IS_INSTALL.getProp(), "true");
        return true;
    }




}
