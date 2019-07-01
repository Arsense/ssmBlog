package com.we.weblog.service.impl;

import com.we.weblog.domain.Category;
import com.we.weblog.domain.result.Result;
import com.we.weblog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author tangwei
 * @date 2018/10/24 10:50
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public Result saveByCategory(Category category) {
        return null;
    }

    @Override
    public Result removeByCategoryId(Integer cateId) {
        return null;
    }

    @Override
    public Result getAllCategories() {
        return null;
    }

    @Override
    public Result findByCateId(Integer id) {
        return null;
    }
}
