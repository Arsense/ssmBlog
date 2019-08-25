package com.we.weblog.service.impl;

import com.we.weblog.service.HomeService;
import com.we.weblog.service.PostService;
import com.we.weblog.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author tangwei
 * @date 2019/7/5 1:37
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private PostService postService;
    @Resource
    private TagService tagService;



    @Override
    public void getViewCommon(Map<String, Object> map, int pageType) {
        int totalTags = 10;

        try {
            map.put("blogs", postService.findHotPosts(5).getData());
            map.put("tagsName",  tagService.getTotalTagsName().getData());
            map.put("tagsCount", totalTags);
            map.put("blogsCount", postService.findPostCount().getData());
            map.put("categoryCount", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
