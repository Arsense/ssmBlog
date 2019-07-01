package com.we.weblog.mapper;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.Post;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Clay
 * @date 2019/7/1 18:39
 */
public class PostMapperTest extends BaseTest {


    @Resource
    private PostMapper postMapper;
    @Test
    public void testSelect() {
        Post post = new Post();
        post.setUid(3);
        System.out.println("结果是: " + postMapper.queryPost(post));

    }
}
