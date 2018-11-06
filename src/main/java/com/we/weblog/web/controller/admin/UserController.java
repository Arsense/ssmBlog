package com.we.weblog.web.controller.admin;

import com.we.weblog.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 用户信息的修改
 *
 * @author tangwei
 * @date 2018/11/5 19:55
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    /**
     *
     *  处理修改用户资料的请求

     * @param user
     * @param result
     * @param session
     */
    @PostMapping(value = "save")
    @ResponseBody
    public void saveProfile(@Valid @ModelAttribute User user, BindingResult result, HttpSession session) {

    }


    @PostMapping(value = "changePass")
    @ResponseBody
    public void changePass(@ModelAttribute("beforePass") String beforePass,
                                 @ModelAttribute("newPass") String newPass,
                                 @ModelAttribute("userId") Long userId,
                                 HttpSession session) {
    }

}
