package com.we.weblog.domain.enums;

/**
 * @author tangwei
 * @date 2018/10/23 18:38
 */
public enum PropertyEnum {
    /**
     * 是否已经安装
     */
    IS_INSTALL("is_install"),

    /**
     * 博客主题
     */
    THEME("theme"),

    /**
     * 启用邮件服务
     */
    SMTP_EMAIL_ENABLE("smtp_email_enable"),

    /**
     * 邮箱服务器地址
     */
    MAIL_SMTP_HOST("mail_smtp_host"),

    /**
     * 邮箱地址
     */
    MAIL_SMTP_USERNAME("mail_smtp_username"),

    /**
     * 邮箱密码／授权码
     */
    MAIL_SMTP_PASSWORD("mail_smtp_password"),

    /**
     * 发送者名称
     */
    MAIL_FROM_NAME("mail_from_name"),
    /**
     * 邮件回复通知
     */
    COMMENT_REPLY_NOTICE("comment_reply_notice"),

    /**
     * 新评论是否需要审核
     */
    NEW_COMMENT_NEED_CHECK("new_comment_need_check"),

    /**
     * 新评论通知
     */
    NEW_COMMENT_NOTICE("new_comment_notice"),

    /**
     * 邮件审核通过通知
     */
    COMMENT_PASS_NOTICE("comment_pass_notice");




    private String prop;

    PropertyEnum(String prop) {
        this.prop = prop;
    }

    public String getProp() {
        return prop;
    }

}
