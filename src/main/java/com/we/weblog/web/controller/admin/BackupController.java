package com.we.weblog.web.controller.admin;

import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.domain.BackFile;
import com.we.weblog.domain.Post;
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
     * 执行备份
     *
     * @param type 备份类型
     * @return
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


    /**
     * 显示后台博客列表
     * @return
     */
    @GetMapping("/table")
    @ResponseBody
    public UIModel baseSourceFromData(@RequestParam(value = "type", defaultValue = "resources") String type) {

        //需要优化
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

        FormModel formModel = new FormModel();
        formModel.createFormItem("fileName").setHidden(false).setLabel("文件名称");
        formModel.createFormItem("createTime").setHidden(false).setLabel("日期");
        formModel.createFormItem("fileSize").setHidden(false).setLabel("文件大小");
        formModel.createFormItem("fileType").setHidden(false).setLabel("文件类型");
        formModel.createFormItem("backupType").setHidden(false).setLabel("操作");

        TableData tableData = new TableData() ;
        tableData.setFormItems(formModel.getFormItems());
        tableData.setDataItems(backups);
        tableData.setTotalSize(50);


        return  UIModel.success().tableData(tableData);

    }

}
