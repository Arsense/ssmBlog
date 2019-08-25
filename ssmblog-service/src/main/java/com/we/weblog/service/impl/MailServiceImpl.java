package com.we.weblog.service.impl;

import com.we.weblog.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Clay
 * @date 2018/12/9 21:57
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Override
    public void sendAttachMail(String mail, String ads, String c, String d) {

    }
}
