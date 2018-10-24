package com.we.weblog.service;

import com.we.weblog.domain.UploadPicture;

import javax.servlet.http.HttpServletRequest;

/**
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
