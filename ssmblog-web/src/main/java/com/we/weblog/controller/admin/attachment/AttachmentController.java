package com.we.weblog.controller.admin.attachment;

import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.controller.core.BaseController;
import com.we.weblog.domain.Attachment;
import com.we.weblog.service.AttachmentService;
import com.we.weblog.service.LogsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Clay
 * @date 2018/12/9 19:46
 */
@Controller
@RequestMapping(value = "/admin/attachments")
public class AttachmentController extends BaseController {

    private final static Logger LOG = LoggerFactory.getLogger(AttachmentController.class);

    @Resource
    private AttachmentService attachmentService;
    @Resource
    private LogsService logsService;

    /**
     * 获取upload的所有图片资源并渲染页面
     *
     * @param model model
     * @return 模板路径admin/admin_attachment
     */
    @GetMapping
    public String attachments(Model model, @RequestParam(defaultValue = "0")Integer page, @RequestParam(defaultValue = "18")Integer size                                ) {
        try {
            int currentPage = 1;
//            List<Attachment> attachments = attachmentService.findAllAttachments(currentPage);
//            model.addAttribute("attachments", attachments);
        } catch (Exception e) {
            return "admin/admin_attachment";
        }
        return "admin/admin_attachment";
    }

    /**
     * 上传附件
     *
     * @return Map
     */
    @PostMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
//        @RequestParam("file") MultipartFile file,
//        return attachmentService.uploadAttachment(null);
        return null;
    }

    /**
     * editor.md上传图片
     *
     * @param file    file
     * @param request request
     * @return Map
     */
    @PostMapping(value = "/upload/editor", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> editorUpload(@RequestParam("editormd-image-file") MultipartFile file,
                                            HttpServletRequest request) {
//      return uploadAttachment(file, request);
        return null;
    }

    /**
     * 跳转选择附件页面 附件详情
     *
     * @param model model
     * @param page  page 当前页码
     * @return 模板路径admin/widget/_attachment-select
     */
    @GetMapping(value = "/select")
    public String selectAttachment(Model model,
               @RequestParam(value = "page", defaultValue = "0") Integer page,
               @RequestParam(value = "id", defaultValue = "none") String id,
               @RequestParam(value = "type", defaultValue = "normal") String type) {

        List<Attachment> attachments = null;
        try {
//            attachments = attachmentService.findAllAttachments(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("attachments", attachments);
        model.addAttribute("id", id);

        if (StringUtils.equals(type, "post")) {
            return "admin/widget/_attachment-select-post";
        }
        return "admin/widget/_attachment-select";

    }

    /**
     * 处理获取附件详情的请求
     *
     * @param model    model
     * @param attachId 附件编号
     * @return 模板路径admin/widget/_attachment-detail
     */
    @GetMapping(value = "/attachment")
    public String attachmentDetail(Model model, @RequestParam("attachId") Integer attachId) {
//        Attachment attachment = null;
//        try {
//            attachment = attachmentService.findByAttachId(attachId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        model.addAttribute("attachment", attachment);
        return "admin/widget/_attachment-detail";
    }

    /**
     * 移除附件的请求
     *
     * @param attachId 附件编号  就是如果int用了Integer 要加@RequestParam("attachId")
     * @param request  request
     * @return UIModel
     */
    @GetMapping(value = "/remove")
    @ResponseBody
    public UIModel removeAttachment(@RequestParam("attachId") Integer attachId,
                                    HttpServletRequest request) {
//        String delFileName = null;
//        try {
//            Attachment attachment = attachmentService.findByAttachId(attachId);
//            delFileName = attachment.getAttachName();
//            String delSmallFileName = delFileName.substring(0, delFileName.lastIndexOf('.')) + "_small" + attachment.getAttachSuffix();
//
//            //删除数据库中的内容
//            attachmentService.removeByAttachId(attachId);
//            //删除文件
//            String userPath = System.getProperties().getProperty("user.home") + "/halo";
//            File mediaPath = new File(userPath, attachment.getAttachPath().substring(0, attachment.getAttachPath().lastIndexOf('/')));
//            File delFile = new File(new StringBuffer(mediaPath.getAbsolutePath()).append("/").append(delFileName).toString());
//            File delSmallFile = new File(new StringBuffer(mediaPath.getAbsolutePath()).append("/").append(delSmallFileName).toString());
//            if (delFile.exists() && delFile.isFile()) {
//                if (delFile.delete() && delSmallFile.delete()) {
//                    LOG.info("删除文件[{}]成功！", delFileName);
////                    logsService.saveByLogs(
////                            new Logs(LogsRecord.REMOVE_FILE, delFileName, ServletUtil.getClientIP(request), DateUtil.date())
////                    );
//                } else {
//                    LOG.error("删除附件[{}]失败！", delFileName);
//                    return UIModel.fail().msg("删除附件失败");
//                }
//            }
//        } catch (Exception e) {
//            LOG.error("删除附件[{}]失败:{}", delFileName, e.getMessage());
//            return UIModel.fail().msg("删除附件失败");
//
//        }
        return UIModel.success().msg("删除附件成功");

    }

}
