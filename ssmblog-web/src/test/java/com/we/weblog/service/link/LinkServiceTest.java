package com.we.weblog.service.link;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.Link;
import com.we.weblog.service.LinkService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Clay
 * @date 2019/3/28 15:09
 */
public class LinkServiceTest  extends BaseTest {


    @Resource
    private LinkService linkService;
    @Test
    public void saveByLink() {
        linkService.saveByLink(new Link());
    }

    @Test
    public void removeByLinkId() {
        linkService.removeByLinkId(1L);
    }

    @Test
    public void findAllLinks() {
        linkService.findAllLinks();
    }

    @Test
    public void findByLinkId() {
        linkService.findByLinkId(1L);

    }
}