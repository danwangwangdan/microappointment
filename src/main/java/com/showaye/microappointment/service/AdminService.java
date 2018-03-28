package com.showaye.microappointment.service;

import com.showaye.microappointment.model.entity.AdminUser;

/**
 * @Author Zhang Yanfu
 * @Date 2018/2/3.
 * @Email 1105564280@qq.com
 */
public interface AdminService {
    AdminUser findAdminByName(String name);
}
