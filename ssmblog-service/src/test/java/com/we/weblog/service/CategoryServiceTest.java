package com.we.weblog.service;

import com.we.weblog.service.base.BaseTest;
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
