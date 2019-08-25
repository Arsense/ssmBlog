package com.we.weblog.service.mail;

import com.we.weblog.BaseTest;
import com.we.weblog.service.MailService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Clay
 * @date 2019/3/28 15:10
 */
public class MailServiceTest extends BaseTest {

    @Resource
    private MailService mailService;

    @Test
    public void sendAttachMail() {
        mailService.sendAttachMail("1","1","1","1");
    }
}