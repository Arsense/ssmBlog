package com.we.weblog.web.controller.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 *     前台文章归档控制器
 * </pre>
 */
public class BaseController {

    protected Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    String redirectTo(String url) {
        StringBuffer rto = new StringBuffer("redirect:");
        rto.append(url);
        return rto.toString();
    }
    /*
        防止XSS攻击
     */
    public static String cleanXSS(String value){
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

}
