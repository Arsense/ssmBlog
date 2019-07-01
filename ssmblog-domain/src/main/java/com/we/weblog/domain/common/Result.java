package com.we.weblog.domain.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回值
 *
 * @author tangwei
 * @date 2019/6/28 18:35
 */
public class Result {
    /**
     * 接口是否调用成功
     */
    private boolean success;

    /**
     * 错误返回码
     */
    private String resultCode;
    private String modelKey = "value";


    private String[] resultCodeParams;
    /**
     * 存储放回值
     */
    private Map<String, Object> result = new HashMap();

    public Result(boolean success) {
        this.success = success;
    }

    public Result() {
    }

    public Object addDefaultModel(Object obj){
        return this.result.put("value", obj);
    }

    public Object addDefaultModel(String key, Object obj) {
        this.modelKey = key;
        return this.result.put(key, obj);
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
