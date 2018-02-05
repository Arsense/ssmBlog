package com.we.weblog.domain;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.time.Instant;
import java.util.Date;

public class Log {


    private String      id;                      //主键
    private Integer     authod_id;               //操作的ID
    private String      data;                    //产生的数据
    private String      action;                  //操作是啥
    private Integer      created;                 //操作的时间
    private String      ip;                      //日志创建的IP

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAuthod_id() {
        return authod_id;
    }

    public void setAuthod_id(Integer authod_id) {
        this.authod_id = authod_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Log(String action, String data, String ip, Integer uid) {
        this.authod_id = uid;
        this.data = data;
        this.action = action;
        this.created = (int) Instant.now().getEpochSecond();
        this.ip = ip;
    }
}
