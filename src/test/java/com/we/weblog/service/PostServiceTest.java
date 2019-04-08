package com.we.weblog.service;

import com.we.weblog.domain.Post;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author tangwei
 * @date 2019/3/28 15:10
 */
public class PostServiceTest extends BaseTest {


    @Autowired
    private PostService postService;

    @Test
    public void findByTagName() {
        String tagName = "";
        List<Post> posts = postService.findByTagName(tagName);
    }

    @Test
    public void updatePostVisit() {
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