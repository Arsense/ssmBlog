package com.we.weblog.service;

import com.we.weblog.domain.Options;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *
 * <pre>
 *     系统配置
 * </pre>
 *
 * @author Clay
 * @date 2018/10/23 18:42
 */
public interface OptionsService {


    /**
     * 保存单个设置选项
     *
     * @param key   key
     * @param value value
     */
    void saveOption(String key, String value);


    /**
     * 保存多个设置选项
     *
     * @param options options
     */
    void saveOptions(Map<String, String> options);

    /**
     * 获取所有设置选项
     *
     * @return Map
     */
    Map<String, String> findAllOptions();


    /**
     * 移除设置选项
     *
     * @param options options
     */
    void removeOption(Options options);



}
