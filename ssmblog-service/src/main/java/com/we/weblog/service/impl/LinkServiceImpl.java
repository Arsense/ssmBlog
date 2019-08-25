package com.we.weblog.service.impl;

import com.we.weblog.domain.Link;
import com.we.weblog.domain.result.Result;
import com.we.weblog.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Clay
 * @date 2018/12/9 23:41
 */
@Service
public class LinkServiceImpl implements LinkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkServiceImpl.class);

    @Override
    public Result saveByLink(Link link) {
        return null;
    }

    @Override
    public Result removeByLinkId(Long linkId) {
        return null;
    }

    @Override
    public Result findAllLinks() {
        return null;
    }

    @Override
    public Result findByLinkId(Long linkId) {
        return null;
    }
}
