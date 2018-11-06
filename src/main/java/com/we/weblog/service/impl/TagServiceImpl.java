package com.we.weblog.service.impl;

import com.we.weblog.domain.Metas;
import com.we.weblog.domain.Select;
import com.we.weblog.domain.Tags;
import com.we.weblog.domain.modal.Types;
import com.we.weblog.mapper.TagMapper;
import com.we.weblog.service.TagService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    public List<String> getTotalTagsName(){
        return tagMapper.findAll();
    }

    public void deleteTag(int uid){
        tagMapper.deleteTagById(uid);
    }

     // 删除category from metas
    public int deleteMetas(String name){
        return tagMapper.deleteCategoryByName(name);
    }

    /**
     * 添加博客标签
     * @param tags
     * @param id
     */
    public  void addBlogTags(String tags,int id){
        List<String> tagList = getTagList(tags);
        for(String tag:tagList){
            tagMapper.insertBlogTag(tag,id);
        }
    }

    /**
     * 分类管理删除标签
      * @param tagName
     * @return
     */
    public int clearTagData(String tagName) {
       int result = tagMapper.deleteTagByName(tagName);
       if (result > 0) {
           tagMapper.deleleTagFromContext(tagName);
       } else {
           return 0;
       }
       return 1;
    }

    /**
     * 添加分类
     * @param name
     * @return
     */
    public int saveCategory(String name) {
        if (name == null)
            return 0;
        Metas category = new Metas();
        category.setName(name);
        category.setType(Types.MATE_CATEGOTY);

        return tagMapper.save(category);
    }


    public List<String> getMates() {
       List<String> categories = new ArrayList<>();
       List<Metas> metas =  tagMapper.selectCategories();
       for (Metas meta: metas) {
           categories.add(meta.getName());
       }
       return categories;
    }



    public List<Select> getCategories(){
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
        return selects;
    }
    /**
     * 更新博客标签 实际是删除重插入
     * @param tags
     * @param id
     */
    public  void updateBlogTag(String tags,int id){
        tagMapper.deleteTagById(id);
        List<String> tagList = getTagList(tags);
        for(String tag:tagList){
            tagMapper.insertBlogTag(tag,id);
        }

    }

    /**
     * 获取所有标签
     *
     * @return List
     */
    @Override
    public List<String> findAllTags() {
        return tagMapper.findAll();
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
