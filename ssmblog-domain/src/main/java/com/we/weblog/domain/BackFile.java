package com.we.weblog.domain;


/**
 * <pre>
 *     博客分类
 * <pre/>
 *
 * @author tangwei
 * @date 2018/11/11 20:45
 */

public class BackFile {

    /**
     * 文件名
     */
    private String fileName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 备份类型
     */
    private String backupType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getBackupType() {
        return backupType;
    }

    public void setBackupType(String backupType) {
        this.backupType = backupType;
    }
}
