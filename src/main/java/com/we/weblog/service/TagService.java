package com.we.weblog.service;

import com.we.weblog.domain.modal.Select;
import java.util.List;

/**
 * @author tangwei
 * @date 2018/10/23 19:35
 */
public interface TagService {


    /**
     * 获取所有标签
     *
     * @return List
     */
     List<String> getTotalTagsName();

    /**
     * 获取所有标签
     *
     * @return List
     */
     void deleteTag(int uid);


    /**
     * 获取所有标签
     *
     * @return List
     */
    int removeByTagId(String name);


    /**
     * 分类管理删除标签
     * @param tagName
     * @return
     */
    int clearTagData(String tagName);

    /**
     * 添加分类
     * @param name
     * @return
     */
    int saveCategory(String name);

    /**
     * 获取所有标签
     *
     * @return List
     */
    List<String> getMates();


    /**
     * 获取所有标签
     *
     * @return List
     */
    List<Select> getCategories();

    /**
     * 获取所有标签
     *
     * @return List
     */
    void updateBlogTag(String tags,int id);

    /**
     * 获取所有标签
     *
     * @return List
     */
    List<String> findAllTags();


    /**
     * 获取所有标签
     *
     * @return List
     */
    void addBlogTags(String tags,int id);



}
