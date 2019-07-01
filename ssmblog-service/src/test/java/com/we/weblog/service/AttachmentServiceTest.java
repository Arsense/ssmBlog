package com.we.weblog.service;

import com.we.weblog.domain.Attachment;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;


/**
 *  附件管理
 *
 * @author tangwei
 * @date 2019/3/28 14:09
 */
public class AttachmentServiceTest extends BaseTest {


    @Resource
    private AttachmentService attachmentService;
    @Test
    public void findAllAttachments() {
        List<Attachment> attachments = attachmentService.findAllAttachments(1);
        Assert.assertNotNull(attachments);
    }

    @Test
    public void findByAttachId() {
    }

    @Test
    public void removeByAttachId() {
    }

    @Test
    public void uploadAttachment() {
    }
}