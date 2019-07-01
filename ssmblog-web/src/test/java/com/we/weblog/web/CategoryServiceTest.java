package com.we.weblog.web;

import com.we.weblog.service.CategoryService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author tangwei
 * @date 2018/11/6 10:38
 */

public class CategoryServiceTest extends BaseTest {
    @Resource
    private CategoryService categoryService;


    @Test
    public void testFindAll(){
        System.out.println("test OK");
    }

}
