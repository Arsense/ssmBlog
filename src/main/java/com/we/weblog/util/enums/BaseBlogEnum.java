package com.we.weblog.util.enums;

/**
 * @author tangwei
 * @date 2018/10/23 18:38
 */
public enum BaseBlogEnum {
    /**
     * 是否已经安装
     */
    IS_INSTALL("is_install");

    private String prop;

    BaseBlogEnum(String prop) {
        this.prop = prop;
    }

    public String getProp() {
        return prop;
    }

}
