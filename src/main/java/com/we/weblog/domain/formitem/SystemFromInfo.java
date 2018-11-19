package com.we.weblog.domain.formitem;

import com.vue.adminlte4j.annotation.UIFormItem;
import java.io.Serializable;

/**
 * @author tangwei
 * @date 2018/9/28 10:59
 */
public class SystemFromInfo implements Serializable {

    @UIFormItem(label = "改什么好呢 待留" )
    private String appName      ;


    @UIFormItem(label = "测试" )
    private String test      ;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
