package com.we.weblog.utils;

import com.vue.adminlte4j.model.form.FormModel;
import org.springframework.util.CollectionUtils;

import java.util.Map;


/**
 * @author Clay
 * @date 2019/7/4 9:21
 */
public class UiModelModelUtil {


    public static FormModel createUIModelForm( Map<String, String> map) {
        FormModel formModel = new FormModel();
        if (CollectionUtils.isEmpty(map)) {
            return formModel;
        }
        for(String key: map.keySet()) {
            String valueName = map.get(key);
            formModel.createFormItem(key).setHidden(false).setLabel(valueName);
        }

        return formModel;
    }
}
