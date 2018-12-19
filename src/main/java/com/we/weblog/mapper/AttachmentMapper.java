package com.we.weblog.mapper;

import com.we.weblog.domain.Attachment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tangwei
 * @date 2018/12/9 19:52
 */
@Mapper
@Repository
public interface AttachmentMapper {


    @Select({"select * from hexo_attach limit #{page},#{size}"})
    List<Attachment> selectAllAttachment(@Param("page")int currentPage,@Param("size") int pageSize);

    @Insert({"insert into hexo_attach (attachId,attachName,attachPath,attachSmallPath,attachType,attachCreated,attachSize) "+
            "values (#{file.attachId},#{file.attachName}," +
            "#{file.attachPath},#{file.attachSmallPath}" +
            ",#{file.attachType},#{file.attachCreated}" +
            ",#{file.attachSize})"})
    int save(@Param("file") Attachment attachment);
}
