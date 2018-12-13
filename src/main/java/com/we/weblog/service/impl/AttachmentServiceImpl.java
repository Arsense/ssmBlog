package com.we.weblog.service.impl;

import cn.hutool.core.date.DateUtil;
import com.we.weblog.domain.Attachment;
import com.we.weblog.mapper.AttachmentMapper;
import com.we.weblog.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tangwei
 * @date 2018/12/9 19:49
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {


    private static final String HOME_DIR = "/Clay";
    @Resource
    private AttachmentMapper attachmentMapper;

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
    private Map<String, Object> uploadAttachment(MultipartFile file) {
        Map<String, Object> result = new HashMap<>(3);
        if (!file.isEmpty()) {
            try {
                //用户目录
                String userPath = System.getProperties().getProperty("user.home") + HOME_DIR;
                //upload的路径
                StringBuffer filePath = new StringBuffer("upload/");
                //获取当前年月以创建目录，如果没有该目录则创建
                filePath.append(DateUtil.thisYear()).append("/").append(DateUtil.thisMonth()).append("/");
                File mediaPath = new File(userPath, filePath.toString());
                if (!mediaPath.exists()) {
                    mediaPath.mkdirs();
                }



            } catch (Exception e) {
                LOG.error("上传文件失败：{}", e.getMessage());
                result.put("success", 0);
            }
        } else {
            LOG.error("文件不能为空");

        }

        return  null;
    }
}
