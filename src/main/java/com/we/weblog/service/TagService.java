package com.we.weblog.service;

import com.we.weblog.domain.Select;
import java.util.List;

/**
 * @author tangwei
 * @date 2018/10/23 19:35
 */
public interface TagService {

     List<String> getTotalTagsName();

     void deleteTag(int uid);

    // 删除category from metas
    int deleteMetas(String name);


    /**
     * 分类管理删除标签
     * @param tagName
     * @return
     */
    int clearTagData(String tagName);
    /**
     * 添加分类
     * @param name
     * @return
     */
    int addCategory(String name);

    List<String> getMates();



    List<Select> getCategories();

}
