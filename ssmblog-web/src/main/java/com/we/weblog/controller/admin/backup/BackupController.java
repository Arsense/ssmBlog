package com.we.weblog.controller.admin.backup;

import cn.hutool.core.io.FileUtil;
import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import com.vue.adminlte4j.model.form.FormModel;
import com.we.weblog.controller.core.BaseController;
import com.we.weblog.domain.BackFile;
import com.we.weblog.domain.User;
import com.we.weblog.domain.enums.FileTypeEnum;
import com.we.weblog.domain.enums.PropertyEnum;
import com.we.weblog.domain.util.BaseConfigUtil;
import com.we.weblog.service.BackupService;
import com.we.weblog.service.MailService;
import com.we.weblog.service.UserService;
import com.we.weblog.utils.UiModelModelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     公共常量
 * </pre>
 * @author Clay
 * @date 2018/11/5 19:57
 */
@Controller
@RequestMapping("/admin/backup")
public class BackupController extends BaseController {

    @Resource
    private BackupService backupService;
    @Resource
    private UserService userService;
    @Resource
    private MailService mailService;
    /**
     * 显示后台博客列表
     * @return
     */
    @GetMapping("/table")
    @ResponseBody
    public UIModel baseSourceFromData(@RequestParam(value = "type", defaultValue = "resources") String type) {
        //需要优化
        List<BackFile> backups = null;
        try {
            if (StringUtils.equals(type, FileTypeEnum.RESOURCE.getKey())) {
                backups = backupService.getBackUps(FileTypeEnum.RESOURCE.getKey());
            } else if (StringUtils.equals(type, FileTypeEnum.DATABASES.getKey())) {
                backups = backupService.getBackUps(FileTypeEnum.DATABASES.getKey());
            } else if (StringUtils.equals(type, FileTypeEnum.POSTS.getKey())) {
                backups = backupService.getBackUps(FileTypeEnum.POSTS.getKey());
            } else {
                backups = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  UIModel.success().tableData(buildUITableData(backups));
    }

    public TableData buildUITableData(List<BackFile> backups) {
        Map<String, String> formMap = new HashMap<>();

        formMap.put("fileName","文件名称");
        formMap.put("createTime","日期");
        formMap.put("fileSize","文件大小");
        formMap.put("fileType","文件类型");
        formMap.put("backupType","操作");

        FormModel formModel = UiModelModelUtil.createUIModelForm(formMap);
        TableData tableData = new TableData();

        tableData.setFormItems(formModel.getFormItems());
        tableData.setDataItems(backups);
        tableData.setPage(true);
        tableData.setPageSize(15);
        tableData.setTotalSize(50);

        return tableData;
    }



    /**
     * 执行备份
     *
     * @param type 备份类型
     * @return
     */
    @GetMapping(value = "doBackup")
    @ResponseBody
    public UIModel doBackup(@RequestParam("type") String type) {
        if (StringUtils.equals(type, FileTypeEnum.RESOURCE.getKey())) {
             backupService.backupResources();
        } else if (StringUtils.equals(type, FileTypeEnum.DATABASES.getKey())) {
             backupService.backupDatabase();
        } else if (StringUtils.equals(type, FileTypeEnum.POSTS.getKey())) {
             backupService.backupPosts();
        } else {
           return UIModel.fail().msg("备份失败");
        }
        return UIModel.success().msg("备份成功");
    }



    /**
     * 删除备份
     * @param fileName 文件名
     * @param type     备份类型
     * @return UIModel
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
     * @return UIModel
     */
    @GetMapping(value = "sendToEmail")
    @ResponseBody
    public UIModel sendToEmail(@RequestParam("fileName") String fileName,
                                  @RequestParam("type") String type,
                                  HttpSession session){

        try {
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
        } catch (Exception e) {
             UIModel.fail().msg("发送邮件服务端异常");
        }

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
//            //TODO 发送邮件 不知道之前的能不能发成功
//            File file = new File(sourcePath);
//            Map<String, Object> content = new HashMap<>(3);
//            try {
//                content.put("fileName", file.getName());
//                content.put("createAt", HaloUtils.getCreateTime(srcPath));
//                content.put("size", HaloUtils.parseSize(file.length()));
//                mailService.sendAttachMail(user.getUserEmail(), "", content, "result/mail_template/mail_attach.ftl", sourcePath);
//            } catch (Exception e) {
//                LOG.error("邮件服务器未配置：{}", e.getMessage());
//            }
        }


    }



}
