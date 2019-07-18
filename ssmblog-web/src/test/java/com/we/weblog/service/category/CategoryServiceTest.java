package com.we.weblog.service.category;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.Category;
import com.we.weblog.service.CategoryService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 *  目前没有实现
 *
 * @author Clay
 * @date 2018/11/6 10:38
 */

public class CategoryServiceTest extends BaseTest {
    @Resource
    private CategoryService categoryService;


    @Test
    public void saveByCategory() {
        categoryService.saveByCategory(new Category());
    }

    @Test
    public void removeCategory() {
        categoryService.removeCategory(1);

    }

    @Test
    public void getAllCategories() {
        categoryService.getAllCategories();

    }

    @Test
    public void findByCateId() {
        categoryService.findByCateId(1);

    }
}
