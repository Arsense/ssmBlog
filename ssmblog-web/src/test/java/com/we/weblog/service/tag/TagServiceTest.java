package com.we.weblog.service.tag;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.Category;
import com.we.weblog.service.TagService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author Clay
 * @date 2019/3/28 15:11
 */
public class TagServiceTest extends BaseTest {

    @Resource
    private TagService tagService;


    @Test
    public void getTotalTagsName() {
        tagService.getTotalTagsName();
    }

    @Test
    public void deleteTag() {
        tagService.deleteTag(1);

    }



    @Test
    public void saveCategory() {
//        tagService.saveCategory();

    }

    @Test
    public void getMates() {
        tagService.getMates();

    }

    @Test
    public void getCategories() {
        tagService.getCategories();

    }

    @Test
    public void updateBlogTag() {
        tagService.updateBlogTag("", 1);

    }

    @Test
    public void findAllTags() {
        tagService.findAllTags();

    }

    @Test
    public void addBlogTags() {
        tagService.addBlogTags("", 1);

    }
}