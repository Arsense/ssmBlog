package com.we.weblog.mapper;

import com.we.weblog.domain.Attachment;
import org.apache.ibatis.annotations.Mapper;
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


    @Select({"select commentId,post_id,created,author,email,content from t_comments"})
    List<Attachment> selectAllAttachment();
}
