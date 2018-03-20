package com.we.weblog.controller.admin;


import com.vue.adminlte4j.model.TableData;
import com.vue.adminlte4j.model.UIModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 *  评论管理页面
 */
@Controller
@RequestMapping("/admin/comments")
public class CommentController {
    /**
     * 前端 评论信息
     * @return
     */
    @GetMapping("/table")
    @ResponseBody
    Map<String,Object> getCommentsdata() {

        UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData() ;

        tableData.configDisplayColumn(TableData.createColumn("content" , "评论内容") );
        tableData.configDisplayColumn(TableData.createColumn("author" , "评论人") );
        tableData.configDisplayColumn(TableData.createColumn("created" , "评论时间" ));
        tableData.configDisplayColumn(TableData.createColumn("mail" , "评论人邮箱" ));
        tableData.configDisplayColumn(TableData.createColumn("status" , "评论状态" ));

        //遍历查询数据库
        tableData.setTotalSize(10);
        uiModel.tableData(tableData);
        return uiModel ;
    }


}
