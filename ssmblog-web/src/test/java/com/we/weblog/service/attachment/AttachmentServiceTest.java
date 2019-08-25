package com.we.weblog.service.attachment;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.result.Result;
import com.we.weblog.service.AttachmentService;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 *  附件管理
 *
 * @author Clay
 * @date 2019/3/28 14:09
 */
public class AttachmentServiceTest extends BaseTest {


    @Resource
    private AttachmentService attachmentService;

    @Test
    public void findByAttachId() {
        Result result = null;
        try {
            result = attachmentService.findByAttachId(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.isTrue(result != null && result.isSuccess());
    }

    @Test
    public void removeByAttachId() {
        Result result =attachmentService.removeByAttachId(1);
        Assert.isTrue(result != null && result.isSuccess());
    }

    @Test
    public void uploadAttachment() {
        MultipartFile file;
        Result result = attachmentService.uploadAttachment(null);
    }
}