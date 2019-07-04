package com.we.weblog.service;



import  java.util.Map;
/** 前端公用的接口
 * @author tangwei
 * @date 2019/7/5 1:35
 */
public interface HomeService {

    /**
     * 获取前端公共信息
     */
    void getViewCommon(Map<String, Object> map, int pageType);
}
