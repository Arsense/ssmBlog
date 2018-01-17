package com.we.weblog.service.Impl;

import com.we.weblog.domain.UploadPicture;
import javax.servlet.http.HttpServletRequest;


public interface FileService {

    UploadPicture loadPicture(HttpServletRequest request) throws Exception;
}
