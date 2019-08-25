package com.we.weblog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.we.weblog.domain.Attachment;
import com.we.weblog.domain.result.ErrorCode;
import com.we.weblog.domain.result.Result;
import com.we.weblog.mapper.AttachmentMapper;
import com.we.weblog.service.AttachmentService;
import com.we.weblog.service.LogsService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Clay
 * @date 2018/12/9 19:49
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private static final Logger LOG = LoggerFactory.getLogger(AttachmentServiceImpl.class);
    private static final String HOME_DIR = "/Clay";
    @Resource
    private AttachmentMapper attachmentMapper;

    @Resource
    private LogsService logsService;


    @Override
    public Result saveFile(Attachment attachment) {
        Result result = new Result();
        if (attachment == null) {
            result.setErrMsg("attachment 不能为空");
            return result;
        }
        try {
            attachmentMapper.saveAttachment(attachment);
            result.setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public Result queryAttach() {
        Result result = new Result();

        try {
            List<Attachment> attachments= attachmentMapper.getAttachments();
            if (!CollectionUtils.isEmpty(attachments)) {
                result.setData(attachments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    @Override
    public Result findByAttachId(int attachId) {
        Result result = new Result();
        if (attachId <= 0){
            result.setErrMsg("参数非法");
            return result;
        }
        try {
            Attachment attachment = attachmentMapper.queryByAttachId(attachId);
            if (attachment != null) {
                result.setData(attachment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    @Override
    public Result removeByAttachId(int attachId) {
        Result result = new Result();
        try {
            attachmentMapper.deleteAttachment(attachId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess();
        return result;
    }

    /**
     * 上传图片
     *
     * @param file    file
     * @return Map
     */
    @Override
    public Result uploadAttachment(MultipartFile file) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>(3);
        if (file.isEmpty()) {
            LOG.error("文件不能为空");
            return result;
        }
        try {
            //用户目录
            String userPath = System.getProperties().getProperty("user.home") + HOME_DIR;
            //upload的路径
            StringBuilder uploadPath = new StringBuilder("upload/");
            //获取当前年月以创建目录，如果没有该目录则创建
            uploadPath.append(DateUtil.thisYear()).append("/").append(DateUtil.thisMonth()).append("/");
            File mediaPath = new File(userPath, uploadPath.toString());
            if (!mediaPath.exists()) {
                if (!mediaPath.mkdirs()) {
                    throw new Exception("创建文件异常");
                }
            }
            String fileFullPath  = buildUploadFilePath(file.getOriginalFilename());
            file.transferTo(new File(mediaPath.getAbsoluteFile(), fileFullPath));
            //压缩图片
            StringBuilder fileName = new StringBuilder(mediaPath.getAbsolutePath());
            StringBuilder compressionPath = new StringBuilder(mediaPath.getAbsolutePath())
                                                        .append("/")
                                                        .append(fileName)
                                                        .append("_small.")
                                                        .append("先随便写");

            fileFullPath = "/upload/" + DateUtil.thisYear() + "/"
                                          + DateUtil.thisMonth() + "/" + fileName;
            Thumbnails.of(fileFullPath)
                      .size(256, 256)
                      .keepAspectRatio(false)
                      .toFile(compressionPath.toString());

            Attachment attachment = new Attachment();
            attachment.setAttachName(fileName.toString());
            attachment.setAttachPath(fileFullPath);
            attachment.setAttachSmallPath(compressionPath.toString());
            attachment.setAttachType(file.getContentType());
            attachment.setAttachSuffix("." + "png");
            attachment.setAttachCreated(DateUtil.date());
            attachment.setAttachSize("1024");

            try {
                attachmentMapper.saveAttachment(attachment);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOG.info("上传文件[{}]到[{}]成功", fileName, mediaPath.getAbsolutePath());
//            logsService.saveByLogs(
//                    new Log(LogsRecord.UPLOAD_FILE, fileName, ServletUtil.getClientIP(request), DateUtil.date())
//            );

            map.put("success", 1);
            map.put("message", "上传成功");
            map.put("url",  attachment.getAttachPath());
            map.put("filename", fileName);
        } catch (Exception e) {
            LOG.error("上传文件失败：{}", e.getMessage());
            map.put("success", 0);
            map.put("message", "上传失败");
        }


        return  null;
    }

    private String buildUploadFilePath(String fileName){
        if(StringUtils.isEmpty(fileName)) {
            return null;
        }
        int suffixIndex = fileName.lastIndexOf('.');
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String name = fileName.substring(0, suffixIndex)
                     + dateFormat.format(System.currentTimeMillis());
        String suffix = fileName.substring(suffixIndex + 1);
        return name + "." + suffix;
    }




}
