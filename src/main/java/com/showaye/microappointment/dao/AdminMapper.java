package com.showaye.microappointment.dao;

import com.showaye.microappointment.model.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Zhang Yanfu
 * @Date 2018/2/3.
 * @Email 1105564280@qq.com
 */
public interface AdminMapper {
    AdminUser findAdminByName(@Param("name") String name);
}
