package com.we.weblog.service.impl;

import com.we.weblog.domain.Metas;
import com.we.weblog.domain.result.Result;
import com.we.weblog.domain.modal.Select;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.mapper.TagMapper;
import com.we.weblog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class TagServiceImpl implements TagService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);


    @Resource
    private TagMapper tagMapper;

    @Override
    public Result getTotalTagsName(){
        Result result = new Result();
        tagMapper.queryTags();
        return result;
    }

    @Override
    public Result deleteTag(int uid){
        Result result = new Result();
        tagMapper.deleteTagById(uid);
        return result;
    }

    @Override
    public Result removeByTagId(String name) {
        Result result = new Result();
        return result;
    }

    // 删除category from metas
    public Result deleteMetas(String name){
        Result result = new Result();
        tagMapper.deleteCategoryByName(name);
        return result;
    }

    /**
     * 添加博客标签
     * @param tags
     * @param id
     */
    @Override
    public  Result addBlogTags(String tags, int id){
        Result result = new Result();
        List<String> tagList = getTagList(tags);
        for (String tag:tagList) {
            tagMapper.insertBlogTag(tag,id);
        }
        return result;
    }



    /**
     * 添加分类
     * @param name
     * @return
     */
    @Override
    public Result saveCategory(String name) {
        Result result = new Result();
        if (name == null) {
            return result;
        }
        Metas category = new Metas();
        category.setName(name);
        category.setType(Types.MATE_CATEGOTY);
        tagMapper.save(category);
        return result;
    }


    @Override
    public Result getMates() {
        Result result = new Result();
        List<String> categories = tagMapper.getAllCategories();

        return result;
    }



    @Override
    public Result getCategories(){
        Result result = new Result();

        List<Select> selects = new ArrayList<>();
        List<String> categories = tagMapper.getAllCategories();

        int codeId = 1;
        for (String category : categories) {
            Select select = new Select();
            select.setCode(String.valueOf(codeId++));
            select.setLabel(category);
            select.setChecked(false);
            selects.add(select);
        }
        return result;
    }
    /**
     * 更新博客标签 实际是删除重插入
     * @param tags
     * @param id
     */
    @Override
    public Result updateBlogTag(String tags, int id){
        Result result = new Result();
        tagMapper.deleteTagById(id);
        List<String> tagList = getTagList(tags);
        for(String tag:tagList) {
            tagMapper.insertBlogTag(tag,id);
        }
        return result;
    }

    /**
     * 获取所有标签
     *
     * @return List
     */
    @Override
    public Result findAllTags() {
        Result result = new Result();
        tagMapper.queryMetas();
        return result;
    }



    /**
     *  将tags拆分放到数组里
     * @param tagString
     * @return
     */
    public List<String> getTagList(String tagString){
        List<String> tagList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(tagString,",");
        while (st.hasMoreTokens()){
            tagList.add(st.nextToken());
        }
        return tagList;
    }
}
