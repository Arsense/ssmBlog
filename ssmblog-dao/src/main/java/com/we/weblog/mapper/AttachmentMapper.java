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

    @Insert({"insert into hexo_attach (attachId,attachName,attachPath,attachSmallPath,attachType,attachCreated,attachSize) "+
            "values (#{file.attachId},#{file.attachName}," +
            "#{file.attachPath},#{file.attachSmallPath}" +
            ",#{file.attachType},#{file.attachCreated}" +
            ",#{file.attachSize})"})
    int save(@Param("file") Attachment attachment);
}
