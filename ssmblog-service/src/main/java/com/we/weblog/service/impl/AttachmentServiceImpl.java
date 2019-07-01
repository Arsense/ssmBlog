package com.we.weblog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.we.weblog.domain.Attachment;
import com.we.weblog.mapper.AttachmentMapper;
import com.we.weblog.service.AttachmentService;
import com.we.weblog.service.LogsService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangwei
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
    public List<Attachment> findAllAttachments(int currentPage) {
        return attachmentMapper.selectAllAttachment(currentPage,15);
    }

    @Override
    public Attachment findByAttachId(int attachId) {
        return null;
    }

    @Override
    public void removeByAttachId(int id) {
    String a;
    }

    /**
     * 上传图片
     *
     * @param file    file
     * @return Map
     */
    @Override
    public Map<String, Object> uploadAttachment(MultipartFile file) {
        Map<String, Object> result = new HashMap<>(3);
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
            attachment.setAttachSuffix(new StringBuffer(".").append("png").toString());
            attachment.setAttachCreated(DateUtil.date());
            attachment.setAttachSize("1024");

            attachmentMapper.save(attachment);
            LOG.info("上传文件[{}]到[{}]成功", fileName, mediaPath.getAbsolutePath());
//            logsService.saveByLogs(
//                    new Log(LogsRecord.UPLOAD_FILE, fileName, ServletUtil.getClientIP(request), DateUtil.date())
//            );

            result.put("success", 1);
            result.put("message", "上传成功");
            result.put("url", attachment.getAttachPath());
            result.put("filename",fileName);
        } catch (Exception e) {
            LOG.error("上传文件失败：{}", e.getMessage());
            result.put("success", 0);
            result.put("message", "上传失败");
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
