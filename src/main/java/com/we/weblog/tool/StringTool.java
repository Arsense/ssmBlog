package com.we.weblog.tool;

public class StringTool {

    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim());
    }

}
