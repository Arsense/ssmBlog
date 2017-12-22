package api.data;


import api.data.MenuApiInJvm;
import com.we.weblog.TableData;
import com.we.weblog.UIModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {


    @GetMapping("/get_app_info")
    @ResponseBody
    Map getAppInfo() {

        Boolean loginStatus = true;
        UIModel uiModel = new UIModel()
                .menu(MenuApiInJvm.getMenu())
                .appInfo(AppInfoInJvm.getAppInfo())
                .isLogin(true);

        TableData tableData = new TableData() ;
        tableData.addColumnsHeader("name" , "Name" );
        tableData.addColumnsHeader("age" , "年龄" );
        Map<String,Object> map = new HashMap<>() ;
        map.put("name" , "xiaohong ") ;
        map.put("age" , "1") ;
        tableData.addData(map);
        uiModel.put("tableData" , tableData ) ;
        //return uiModel ;
        return uiModel ;
    }

    @GetMapping("/get_table_data")
    @ResponseBody
    Map<String,Object> get_table_data() {
        UIModel uiModel = new UIModel() ;
        TableData tableData = new TableData() ;
        tableData.addColumnsHeader("name" , "Name" );
        tableData.addColumnsHeader("age" , "年龄" );
        Map<String,Object> map = new HashMap<>() ;
        map.put("name" , "ajax-data ") ;
        map.put("age" , "1-1") ;
        tableData.addData(map);
        uiModel.put("tableData" , tableData ) ;

        //return uiModel ;
        return uiModel ;
    }



}
