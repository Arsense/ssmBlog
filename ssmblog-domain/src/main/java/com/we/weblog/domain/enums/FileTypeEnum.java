package com.we.weblog.domain.enums;

/**
 * @author tangwei
 * @date 2019/7/18 15:01
 */
public enum FileTypeEnum {
    /**
     *  文件资源备份
     */
    RESOURCE(1, "resources"),
    /**
     * 数据库备份
     */
    DATABASES(2, "databases"),
    /**
     * 文章备份
     */
    POSTS(3, "posts");

    int type;
    String key;


    FileTypeEnum(int type, String key) {
        this.type = type;
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
