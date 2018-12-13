package com.we.weblog.service;

import com.we.weblog.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tangwei
 * @date 2018/12/9 19:49
 */
public interface AttachmentService {

    /**
     * 查询所有附件，分页
     *
     * @return Page
     */
    List<Attachment> findAllAttachments(int currentPage);

    /**
     * 根据Id查找附件
     *
     * @return Page
     */
    Attachment findByAttachId(int attachId);

    /**
     * 根据Id删除附件
     * @param id
     */
    void removeByAttachId(int id);

    Map<String, Object> uploadAttachment(MultipartFile file);



}
