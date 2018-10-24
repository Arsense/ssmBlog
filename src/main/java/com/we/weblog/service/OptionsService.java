package com.we.weblog.service;

import java.util.Map;

/**
 *
 * <pre>
 *     系统配置
 * </pre>
 *
 * @author tangwei
 * @date 2018/10/23 18:42
 */
public interface OptionsService {

    /**
     * 获取所有设置选项
     *
     * @return Map
     */
    Map<String, String> findAllOptions();


}
