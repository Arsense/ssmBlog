package com.we.weblog.domain.enums;

/**
 * @author tangwei
 * @date 2018/12/27 18:40
 */
public enum PostStatus {
    /**
     * 已发布
     */
    PUBLISHED(0, "已发布"),
    /**
     * 草稿
     */
    DRAFT(1, "草稿"),
    /**
     * 回收站
     */
    RECYCLE(2, "回收站");

    private Integer code;
    private String desc;

    PostStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
