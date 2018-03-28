package com.showaye.microappointment.dto;

import com.showaye.microappointment.model.entity.AdminUser;

import java.util.Date;

/**
 * @Author Zhang Yanfu
 * @Date 2018/2/4.
 * @Email 1105564280@qq.com
 */
public class AdminUserDto {
    //登录存放session的名字 session name
    public static final String SESSION_NAME = "LOGIN_USER";
    //登录的用户
    private AdminUser adminUser;
    //登录的IP
    private String login_ip;
    //登录的时间

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }

    private Date login_time;


}
