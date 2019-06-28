package com.we.weblog.domain;

import java.util.Date;

/**
 * @author tangwei
 * @date 2018/12/9 19:48
 */
public class Attachment {
    /**
     * 附件编号
     */
    private Long attachId;

    /**
     * 附件名
     */
    private String attachName;

    /**
     * 附件路径
     */
    private String attachPath;

    /**
     * 附件缩略图路径
     */
    private String attachSmallPath;

    /**
     * 附件类型
     */
    private String attachType;

    /**
     * 附件后缀
     */
    private String attachSuffix;

    /**
     * 上传时间
     */
    private Date attachCreated;

    /**
     * 附件大小
     */
    private String attachSize;

    /**
     * 附件长宽
     */
    private String attachWidth;

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getAttachSmallPath() {
        return attachSmallPath;
    }

    public void setAttachSmallPath(String attachSmallPath) {
        this.attachSmallPath = attachSmallPath;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public String getAttachSuffix() {
        return attachSuffix;
    }

    public void setAttachSuffix(String attachSuffix) {
        this.attachSuffix = attachSuffix;
    }

    public Date getAttachCreated() {
        return attachCreated;
    }

    public void setAttachCreated(Date attachCreated) {
        this.attachCreated = attachCreated;
    }

    public String getAttachSize() {
        return attachSize;
    }

    public void setAttachSize(String attachSize) {
        this.attachSize = attachSize;
    }

}
