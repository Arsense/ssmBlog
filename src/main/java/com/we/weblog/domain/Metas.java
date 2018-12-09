package com.we.weblog.domain;

/**
 *
 *  * <pre>
 *     博客分类
 * <pre/>
 * @author tangwei
 * @date 2018/3/25 16:33
 * 元数据
 */
public class Metas {

    private int id;
    private String name;

    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
