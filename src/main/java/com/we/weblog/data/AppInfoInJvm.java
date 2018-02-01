package com.we.weblog.data;


import com.we.weblog.AppInfo;

public class AppInfoInJvm {



    public static AppInfo getAppInfo() {
        AppInfo appInfo = new AppInfo() ;
        appInfo.setUserName("小狗欢迎你");
        appInfo.setIndexUrl("/index.html");
        appInfo.setSignOutUrl("/.html");
        appInfo.setProfileUrl("/profile.html");
        appInfo.setUserImgUrl("");
        appInfo.setLogoName("博客管理系统");
        appInfo.setLogoShortName("Clay");
        return  appInfo;
    }


}
