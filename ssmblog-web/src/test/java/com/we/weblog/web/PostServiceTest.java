package com.we.weblog.web;

import com.we.weblog.domain.result.Result;
import com.we.weblog.service.PostService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author tangwei
 * @date 2019/3/28 15:10
 */
public class PostServiceTest extends BaseTest {


    @Resource
    private PostService postService;

    @Test
    public void findByTagName() {
        String tagName = "标签";
        Result result = postService.findByTagName(tagName);
        Assert.assertTrue(result.isSuccess());
        System.out.println("最后的结果是:" + result.getData());

    }

    @Test
    public void updatePostVisit() {
        System.out.println("ddddd");
    }

    @Test
    public void getCategoryCount() {


    }

    @Test
    public void getLastestBlogId() {


    }

    @Test
    public void sortBlogsByCategories() {


    }

    @Test
    public void findAuthor() {
    }

    @Test
    public void updatePost() {
    }

    @Test
    public void findPostsByTagName() {
    }

    @Test
    public void findPostByYearAndMonth() {
    }

    @Test
    public void findPostCount() {
    }

    @Test
    public void getArticlePages() {
    }

    @Test
    public void removePostCategory() {
    }

    @Test
    public void saveByPost() {
    }

    @Test
    public void findNextPost() {
    }

    @Test
    public void findPreviousPost() {
    }

    @Test
    public void removeByPostId() {
    }

    @Test
    public void updatePostStatus() {
    }

    @Test
    public void findAllPosts() {
    }

    @Test
    public void findAllPosts1() {
    }

    @Test
    public void findAllPostsByStatus() {
    }

    @Test
    public void findByPostId() {
    }

    @Test
    public void findLastestPost() {
    }
}