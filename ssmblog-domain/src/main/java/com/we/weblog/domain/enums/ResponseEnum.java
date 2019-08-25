package com.we.weblog.domain.enums;

/**
 * @author tangwei
 * @date 2019/7/5 11:25
 */
public enum ResponseEnum implements ErrorCode{
    /**
     *
     */
    SUCCESS(200, "成功"),
    PARAM_ERROR(202, "传入参数错误"),
    SERVER_ERROR(500, "服务端异常");

    private int key;
    private String desc;

    ResponseEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }


    @Override
    public int getErrCode() {
        return key;
    }

    @Override
    public String getErrMsg() {
        return desc;
    }
}
