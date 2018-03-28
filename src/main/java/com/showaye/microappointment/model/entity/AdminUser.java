package com.showaye.microappointment.model.entity;

import com.showaye.microappointment.model.base.BaseModel;

/**
 * @Author Zhang Yanfu
 * @Date 2018/2/3.
 * @Email 1105564280@qq.com
 */
public class AdminUser extends BaseModel {

    private static final long serialVersionUID = 4600955178114394197L;

    private int id;
    private String adminName;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
