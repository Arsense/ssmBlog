package com.we.weblog.service;

import com.we.weblog.domain.common.Result;

/**
 * @author tangwei
 * @date 2018/12/9 21:56
 */
public interface MailService {

    void sendAttachMail(String mail, String ads, String c, String d);

}
