package com.we.weblog.mapper;

import com.we.weblog.domain.Attachment;
import com.we.weblog.mapper.builder.AttachmentSqlBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Clay
 * @date 2018/12/9 19:52
 */
@Mapper
@Repository
public interface AttachmentMapper {


    @SelectProvider(type = AttachmentSqlBuilder.class, method = "buildAttachmentQuery")
    List<Attachment> queryAttachments(@Param("page") int currentPage, @Param("size") int pageSize);

    @SelectProvider(type = AttachmentSqlBuilder.class, method = "buildAttachmentsQuery")
    List<Attachment> getAttachments();

    @InsertProvider(type = AttachmentSqlBuilder.class, method = "buildInsert")
    int saveAttachment(@Param("file") Attachment attachment);

    @SelectProvider(type = AttachmentSqlBuilder.class, method = "buildAttachmentIdQuery")
    Attachment queryByAttachId(int attachId);


    @DeleteProvider(type = AttachmentSqlBuilder.class, method = "buildDelete")
    int deleteAttachment(int attachId);

}
