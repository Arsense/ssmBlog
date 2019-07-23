package com.we.weblog.mapper.builder;

/**
 * @author Clay
 * @date 2019/7/1 23:57
 */
public class AttachmentSqlBuilder {

    /**
     *
     * @return
     */
    public String buildAttachmentQuery() {
        return "select * from hexo_attach limit #{page},#{size}";
    }

    /**
     *
     * @return
     */
    public String buildAttachmentsQuery() {
        return "select * from hexo_attach ";
    }

    /**
     *
     * @return
     */
    public String buildAttachmentIdQuery() {
        return "select * from hexo_attach where attachId = #{attachId}";
    }

    /**
     *
     * @return
     */
    public String buildDelete() {
        return "delete  from hexo_attach where attachId = #{attachId}";
    }

    /**
     * todo 后续优化
     * @return
     */
    public String buildInsert() {
        return "insert into hexo_attach (attachId,attachName,attachPath,attachSmallPath,attachType,attachCreated,attachSize) "+
                "values (#{file.attachId},#{file.attachName}," +
                "#{file.attachPath},#{file.attachSmallPath}" +
                ",#{file.attachType},#{file.attachCreated}" +
                ",#{file.attachSize})";
    }
}
