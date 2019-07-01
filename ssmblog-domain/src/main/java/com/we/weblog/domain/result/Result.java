package com.we.weblog.domain.result;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回值
 *
 * @author Clay
 * @date 2019/6/28 18:35
 */
public class Result<T>  {
    /**
     * 接口是否调用成功
     */
    private boolean success;

    /**
     * 错误返回码
     */
    private int errCode;
    private String errMsg;
    private T data;

    /**
     * 存储放回值
     */
    private Map<String, Object> result = new HashMap();

    public Result(boolean success) {
        this.success = success;
    }

    public Result() {
    }

    public void setFailure(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                ", data=" + data +
                ", result=" + result +
                '}';
    }
}
