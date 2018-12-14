package com.we.weblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import java.util.Date;


/**
 * <pre>
 *     用户信息
 * <pre/>
 */
public class User {

    /**
     *  用户id
     */
    private int userId ;
    /**
     *  用户姓名
     */
    private String userName ;
    /**
     *  用户姓名
     */
    private String password ;
    /**
     * 显示名称
     */
    private String userDisplayName;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String userEmail;
    /**
     * 是否禁用登录
     */
    @JsonIgnore
    private String loginEnable = "false";
    /**
     * 最后一次登录时间
     */
    @JsonIgnore
    private Date loginLast;

    /**
     * 登录错误次数记录
     */
    @JsonIgnore
    private Integer loginError = 0;


    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getLoginEnable() {
        return loginEnable;
    }

    public void setLoginEnable(String loginEnable) {
        this.loginEnable = loginEnable;
    }

    public Date getLoginLast() {
        return loginLast;
    }

    public void setLoginLast(Date loginLast) {
        this.loginLast = loginLast;
    }

    public Integer getLoginError() {
        return loginError;
    }

    public void setLoginError(Integer loginError) {
        this.loginError = loginError;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
