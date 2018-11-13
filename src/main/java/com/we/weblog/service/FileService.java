package com.we.weblog.service;

import com.we.weblog.domain.modal.UploadPicture;

import javax.servlet.http.HttpServletRequest;

/**
 *  <pre>
 *     附件文件管理接口
 * </pre>
 *
 * @author tangwei
 * @date 2018/10/23 19:34
 */
public interface FileService {

    /**
     * 上传图片
     * @param request
     * @return
     * @throws Exception
     */
     UploadPicture loadPicture(HttpServletRequest request) throws Exception;
}
