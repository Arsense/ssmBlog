package com.we.weblog.web.controller.admin;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.service.LogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 后端主要管理页面  日志清理和index显示内容
 *
 * @author tangwei
 * @date 2018/11/5 19:57
 */
@Controller
@RequestMapping("/admin")

public class MainAdminController {

    private static final Logger LOG = LoggerFactory.getLogger(MainAdminController.class);

    @Resource
    private LogsService logsService;



    /**
     * 清除所有日志
     *
     * @return
     */
    @GetMapping(value = "/logs/clear")
    public UIModel logsClear() {
        try {
            logsService.removeAllLogs();
        } catch (Exception e) {
            LOG.error("清除日志失败：{}" + e.getMessage());
            return UIModel.fail().msg("日志清理成功");
        }
        return UIModel.success().msg("日志清理成功");
    }
}
