package com.we.weblog.service.impl;

import com.we.weblog.domain.Link;
import com.we.weblog.service.LinkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangwei
 * @date 2018/12/9 23:41
 */
@Service
public class LinkServiceImpl implements LinkService{

    @Override
    public Link saveByLink(Link link) {
        return null;
    }

    @Override
    public Link removeByLinkId(Long linkId) {
        return null;
    }

    @Override
    public List<Link> findAllLinks() {
        return null;
    }

    @Override
    public Link findByLinkId(Long linkId) {
        return null;
    }
}
