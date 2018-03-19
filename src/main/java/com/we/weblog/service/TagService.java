package com.we.weblog.service;


import com.vue.adminlte4j.model.UIModel;
import com.we.weblog.mapping.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {




    private TagMapper tagMapper;
    /**
     *  构造函数
     */
    @Autowired
    TagService(TagMapper tagMapper){
        this.tagMapper = tagMapper;

    }


    public List<String> getTotalTagsName(){
        return tagMapper.selectAllKindTags();
    }


    public void deleteTag(int uid){
        tagMapper.deleteTagById(uid);
    }


    /**
     * 分类管理删除标签
      * @param tagName
     * @return
     */
    public int clearTagData(String tagName){

       int result = tagMapper.deleteTagByName(tagName);
       if(result > 0){
           tagMapper.deleleTagFromContext(tagName);
       }else{
           return 0;
       }
       return 1;
    }
}
