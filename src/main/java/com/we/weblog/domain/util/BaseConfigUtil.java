package com.we.weblog.domain.util;

import org.springframework.ui.context.Theme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * <pre>
 *     公共常量
 * </pre>
 *
 * @author tangwei
 * @date 2018/10/23 18:28
 */
public class BaseConfigUtil {

    /**
     * 所有设置选项（key,value）
     */
    public static Map<String, String> OPTIONS = new HashMap<>();

    /**
     * 所有主题
     */
    public static List<Theme> THEMES = new ArrayList<>();

    /**
     * user_session
     */
    public static String USER_SESSION_KEY = "user_session";

}
