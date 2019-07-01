package com.we.weblog.service;

import com.we.weblog.domain.Link;

import java.util.List;

/**
   <pre>
 *     友情链接业务逻辑接口
 * </pre>
 * @author tangwei
 * @date 2018/12/9 23:41
 */
public interface LinkService {

    /**
     * 新增/修改友情链接
     *
     * @param link link
     * @return Link
     */
    Link saveByLink(Link link);

    /**
     * 根据编号删除
     *
     * @param linkId linkId
     * @return Link
     */
    Link removeByLinkId(Long linkId);

    /**
     * 查询所有
     *
     * @return List
     */
    List<Link> findAllLinks();

    /**
     * 根据编号查询单个链接
     *
     * @param linkId linkId
     * @return Link
     */
    Link findByLinkId(Long linkId);
}
