package com.we.weblog.service.admin;

import com.we.weblog.domain.Attachment;
import com.we.weblog.service.AttachmentService;
import com.we.weblog.service.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tangwei
 * @date 2018/12/13 16:47
 */
public class AttachmentTest extends BaseTest {

    @Resource
    private AttachmentService attachmentService;

    @Test
    public void testfind(){
        List<Attachment> attachments = attachmentService.findAllAttachments(1);
        Assert.assertNotNull(attachments);
    }
}
