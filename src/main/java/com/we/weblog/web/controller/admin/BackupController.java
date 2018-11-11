package com.we.weblog.web.controller.admin;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.domain.BackFile;
import com.we.weblog.service.BackupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author tangwei
 * @date 2018/11/5 19:57
 */
@Controller
@RequestMapping("/admin/backup")
public class BackupController {

    @Resource
    private BackupService backupService;

    /**
     * 查询备份  前端展示
     *
     * @param model model
     * @return 模板路径admin/admin_backup
     */
    @GetMapping
    public String backup(@RequestParam(value = "type", defaultValue = "resources") String type, Model model){
        List<BackFile> backups = null;
        if (StringUtils.equals(type, "resources")) {
            backups = backupService.getBackUps("resources");
        } else if (StringUtils.equals(type, "databases")) {
            backups = backupService.getBackUps("databases");
        } else if (StringUtils.equals(type, "posts")) {
            backups = backupService.getBackUps("posts");
        } else {
            backups = new ArrayList<>();
        }
        model.addAttribute("backups", backups);
        model.addAttribute("type", type);
        return "admin/backup";
    }


    /**
     * 执行备份
     *
     * @param type 备份类型
     * @return JsonResult
     */
    @GetMapping(value = "doBackup")
    @ResponseBody
    public UIModel doBackup(@RequestParam("type") String type) {
        if (StringUtils.equals(type, "resources")) {
             backupService.backupResources();
        } else if (StringUtils.equals(type, "databases")) {
             backupService.backupDatabase();
        } else if (StringUtils.equals(type, "posts")) {
             backupService.backupPosts();
        } else {
           return UIModel.fail().msg("备份失败");
        }

        return UIModel.success().msg("备份成功");

    }


}
