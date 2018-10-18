package com.we.weblog.domain;


import java.util.Date;

public class Log {
    /**
     * 编号 主键
     */
    private Integer  id;
    /**
     * 操作
     */
    private String   action;
    /**
     * 产生的数据
     */
    private String   data;
    /**
     * 操作的ID
     */
    private Integer  author_id;
    /**
     * 编号 主键
     */
    private String  ip;
    /**
     * 日志创建的IP
     */
    private Date  created;
    /**
     * 操作的时间
     */
    private String  dateFormat;






    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
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
        this.created = new Date(System.currentTimeMillis());
        this.ip = ip;
    }
    public  Log(){

    }

}
