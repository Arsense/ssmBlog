package com.we.weblog.service.impl;

import com.we.weblog.domain.Attachment;
import com.we.weblog.mapper.AttachmentMapper;
import com.we.weblog.service.AttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tangwei
 * @date 2018/12/9 19:49
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {


    @Resource
    private AttachmentMapper attachmentMapper;

    @Override
    public List<Attachment> findAllAttachments(int currentPage) {
        return attachmentMapper.selectAllAttachment();
    }

    @Override
    public Attachment findByAttachId(int attachId) {
        return null;
    }

    @Override
    public void removeByAttachId(int id) {
    String a;
    }
}
