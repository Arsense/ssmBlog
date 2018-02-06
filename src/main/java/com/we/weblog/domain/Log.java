package com.we.weblog.domain;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.time.Instant;
import java.util.Date;

public class Log {


    private Integer      id;                      //主键
    private String       action;                  //操作是啥
    private String       data;                    //产生的数据
    private Integer      author_id;               //操作的ID
    private String       ip;                      //日志创建的IP
    private Integer      created;                 //操作的时间


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public int getId() {
        return id;
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
        this.author_id = uid;
        this.data = data;
        this.action = action;
        this.created = (int) Instant.now().getEpochSecond();
        this.ip = ip;
    }
    public  Log(){

    }

}
