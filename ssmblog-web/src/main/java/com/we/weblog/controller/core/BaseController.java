package com.we.weblog.controller.core;

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

    protected final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    public static String HOME_DIR = "/clay";

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    public static String THEME = "hexo";


    /**
     * 根据主题名查找主题
     *
     * @param url
     * @return
     */
    protected String redirectTo(String url) {
        return "themes/" + THEME + url;
    }

    /**
     * 渲染404页面
     *
     * @return redirect:/404
     */
    public String pageNotFound() {
        return "redirect:/404";
    }

    /**
     * 防止XSS过滤

     *
      * @param value
     * @return
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
