package com.showaye.microappointment.service.impl;

import com.showaye.microappointment.dao.AdminMapper;
import com.showaye.microappointment.model.entity.AdminUser;
import com.showaye.microappointment.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Zhang Yanfu
 * @Date 2018/2/3.
 * @Email 1105564280@qq.com
 */
@Service("adminService")
public class AdminServiceImpl  implements AdminService{
    private static Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public AdminUser findAdminByName(String name) {
        AdminUser aadminUser = adminMapper.findAdminByName(name);
        return aadminUser;
    }
}
