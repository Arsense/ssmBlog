package com.we.weblog.web.controller.admin;

import cn.hutool.core.io.FileUtil;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.domain.BackFile;
import com.we.weblog.domain.Post;
import com.we.weblog.domain.User;
import com.we.weblog.service.BackupService;
import com.we.weblog.service.UserService;
import com.we.weblog.util.BaseConfigUtil;
import com.we.weblog.util.enums.PropertyEnum;
import com.we.weblog.web.controller.core.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * @author tangwei
 * @date 2018/11/5 19:57
 */
@Controller
@RequestMapping("/admin/backup")
public class BackupController extends BaseController{

    @Resource
    private BackupService backupService;
    @Resource
    private UserService userService;


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


    /**
     * 删除备份
     * @param fileName 文件名
     * @param type     备份类型
     * @return JsonResult
     */
    @GetMapping(value = "delBackup")
    @ResponseBody
    public UIModel deleteBackup(@RequestParam("fileName") String fileName,
                                @RequestParam("type") String type) {
        String srcPath = System.getProperties().getProperty("user.home") + "/halo/backup/" + type + "/" + fileName;
        try {
            FileUtil.del(srcPath);
            return UIModel.success().msg("备份删除成功");
        } catch (Exception e) {
            return UIModel.success().msg("备份删除失败");
        }

    }


    /**
     * 将备份发送到邮箱
     *
     * @param fileName 文件名
     * @param type     备份类型
     * @return JsonResult
     */
    @GetMapping(value = "sendToEmail")
    @ResponseBody
    public UIModel sendToEmail(@RequestParam("fileName") String fileName,
                                  @RequestParam("type") String type,
                                  HttpSession session){

        String sourcePath = System.getProperties().getProperty("user.home") + "/halo/backup/" + type + "/" + fileName;
        //获取当前登录User
        User user = userService.findUser();
        if (null == user.getUserEmail() || StringUtils.equals(user.getUserEmail(), "")) {
           return UIModel.fail().msg("没有配置email");
        }
        if (StringUtils.equals(BaseConfigUtil.OPTIONS.get(PropertyEnum.SMTP_EMAIL_ENABLE.getProp()), "true")) {
            return UIModel.fail().msg("邮件服务没有设置");
        }
        new EmailToAdmin(sourcePath, user).start();

        return UIModel.success().msg("发送到邮件成功");
    }

    class EmailToAdmin extends Thread {
        private String sourcePath;
        private User user;

        private EmailToAdmin(String sourcePath, User user) {
            this.sourcePath = sourcePath;
            this.user = user;
        }

        @Override
        public void run() {
            //TODO 发送邮件 不知道之前的能不能发成功
        }


    }



}
