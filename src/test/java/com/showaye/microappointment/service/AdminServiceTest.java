package com.showaye.microappointment.service;

import com.showaye.microappointment.model.entity.AdminUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Author Zhang Yanfu
 * @Date 2018/2/4.
 * @Email 1105564280@qq.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springContext.xml")
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;
    @Test
    public void testAdminUserService(){
        AdminUser yanfu = adminService.findAdminByName("yanfu");
        System.out.println(yanfu.toString());
    }
}