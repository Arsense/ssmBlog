package com.we.weblog;

public class AppInfo {
    private String indexUrl = "";
    private String appName  = "app_name" ;
    private String userName = "no name" ;
    private String userImgUrl = "#";
    private String profileUrl = "#";
    private String signOutUrl = "#" ;
    private String logoName = "";

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public String getLogoShortName() {
        return logoShortName;
    }

    public void setLogoShortName(String logoShortName) {
        this.logoShortName = logoShortName;
    }

    private String logoShortName = "" ;
}
