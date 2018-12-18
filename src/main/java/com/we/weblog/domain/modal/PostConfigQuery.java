package com.we.weblog.domain.modal;

import com.vue.adminlte4j.annotation.DictData;
import com.vue.adminlte4j.annotation.DictEntry;
import com.vue.adminlte4j.annotation.UIFormItem;
import com.vue.adminlte4j.model.form.FormItemType;

/**
 * @author tangwei
 * @date 2018/12/18 15:09
 */
public class PostConfigQuery {

    @UIFormItem(span = 3, label = "博客编号")
    private String blogId;
    @UIFormItem(span = 3, label = "标题")
    private String title;
    @UIFormItem(span = 3, label = "标签")
    private Long tags;
    @UIFormItem(type = FormItemType.SELECT, span = 3, label = "分类选择")
    @DictData({@DictEntry(code = "101", value = "博客编号"),
            @DictEntry(code = "107", value = "name"),
            @DictEntry(code = "1002", value = "默认分类"),
            @DictEntry(code = "1001",value = "还是写死的")})
    private String category;

    public PostConfigQuery() {
    }


    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTags() {
        return tags;
    }

    public void setTags(Long tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
