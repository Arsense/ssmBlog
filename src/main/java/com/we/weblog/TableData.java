package com.we.weblog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjliuyong on 2017/12/13.
 */
public class TableData {

    public static final String KEY_TAG = "key" ;
    public static final String TITLE_TAG = "title" ;

    private List<Map<String,String>> columns = new ArrayList<>();
    private List<Object> dataItems = new ArrayList<>();

    public void addColumnsHeader(String key , String title) {
        Map<String,String> col = new HashMap<>() ;
        col.put(KEY_TAG , key) ;
        col.put(TITLE_TAG , title) ;
        columns.add(col) ;
    }

    public void addData(Object o) {
        dataItems.add(o);
    }

    public List<Map<String, String>> getColumns() {
        return columns;
    }

    public List<Object> getDataItems() {
        return dataItems;
    }
}
