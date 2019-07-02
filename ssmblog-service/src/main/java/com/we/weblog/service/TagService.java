package com.we.weblog.service;

import com.we.weblog.domain.result.Result;

/**
 * @author Clay
 * @date 2018/10/23 19:35
 */
public interface TagService {


    /**
     * 获取所有标签
     *
     * @return List
     */
    Result getTotalTagsName();

    /**
     * 获取所有标签
     *
     * @return List
     */
    Result deleteTag(int uid);


    /**
     * 获取所有标签
     *
     * @return List
     */
    Result removeByTagId(String name);


    /**
     * 添加分类
     * @param name
     * @return
     */
    Result saveCategory(String name);

    /**
     * 获取所有标签
     *
     * @return List
     */
    Result getMates();


    /**
     * 获取所有标签
     *
     * @return List
     */
    Result getCategories();

    /**
     * 获取所有标签
     *
     * @return List
     */
    Result updateBlogTag(String tags, int id);

    /**
     * 获取所有标签
     *
     * @return List
     */
    Result findAllTags();


    /**
     * 获取所有标签
     *
     * @return List
     */
    Result addBlogTags(String tags, int id);



}
