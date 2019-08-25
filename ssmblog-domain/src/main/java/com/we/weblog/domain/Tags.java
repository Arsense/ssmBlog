package com.we.weblog.domain;


/**
 * <pre>
 *     博客分类
 * <pre/>
 */
public class Tags {
    /**
     * 标签编号
     */
    private  int tagId;
    /**
     * 标签名称
     */
    private  String tagName;
    /**
     * 标签路径
     */
    private String tagUrl;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagUrl() {
        return tagUrl;
    }

    public void setTagUrl(String tagUrl) {
        this.tagUrl = tagUrl;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagUrl='" + tagUrl + '\'' +
                '}';
    }
}
