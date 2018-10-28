package com.we.weblog.service.impl;

import com.we.weblog.domain.Category;
import com.we.weblog.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangwei
 * @date 2018/10/24 10:50
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public Category saveByCategory(Category category) {
        return null;
    }

    @Override
    public Category removeByCategoryId(Integer cateId) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}
