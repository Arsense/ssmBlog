package com.we.weblog.service;

import com.we.weblog.domain.Attachment;
import com.we.weblog.domain.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Clay
 * @date 2018/12/9 19:49
 */
public interface AttachmentService {


    Result saveFile(Attachment attachment);
    /**
     * 查询所有上传附件
     * @return
     */
    Result queryAttach();


    /**
     * 根据Id查找附件
     *
     * @return Page
     */
    Result findByAttachId(int attachId);

    /**
     * 根据Id删除附件
     * @param attachId
     */
    Result removeByAttachId(int attachId);

    /**
     * 上传附件
     * @param file
     * @return
     */
    Result uploadAttachment(MultipartFile file);


}
