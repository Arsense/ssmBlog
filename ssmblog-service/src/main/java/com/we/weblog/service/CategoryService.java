package com.we.weblog.service;

import com.we.weblog.domain.Category;
import com.we.weblog.domain.common.Result;

import java.util.List;

/**
 * <pre>
 *     评论业务逻辑接口
 * </pre>
 *
 * @author tangwei
 * @date 2018/10/24 10:49
 */
public interface CategoryService {

    /**
     * 新增/修改分类目录
     *
     * @param category 分类目录
     * @return 如果插入成功，返回分类目录对象
     */
    Result saveByCategory(Category category);


    /**
     * 根据编号删除分类目录
     *
     * @param cateId 分类目录编号
     * @return category
     */
    Result removeByCategoryId(Integer cateId);

    /**
     * 获取所有分类目录
     *
     * @return 返回List集合
     */
    Result getAllCategories();

    Result findByCateId(Integer id);

}
