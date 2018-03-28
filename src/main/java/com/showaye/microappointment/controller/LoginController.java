package com.showaye.microappointment.controller;

import com.showaye.microappointment.dto.AdminUserDto;
import com.showaye.microappointment.model.entity.AdminUser;
import com.showaye.microappointment.service.AdminService;
import com.showaye.microappointment.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @Author Zhang Yanfu
 * @Date 2018/2/3.
 * @Email 1105564280@qq.com
 */
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;
    @RequestMapping(path = "/login")
    public String login(Model model, HttpServletRequest request){
        String errMsg = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AdminUser adminUser = adminService.findAdminByName(username);
        try {
            if(adminUser==null){
                errMsg="用户不存在";
            }else if(!adminUser.getPassword().endsWith(SecurityUtil.md5(username,password))){
                errMsg="用户名密码不正确";
            }else{
                AdminUserDto adminUserDto =  new AdminUserDto();
                adminUserDto.setLogin_ip(request.getRemoteAddr());
                adminUserDto.setLogin_time(new Date());
                adminUserDto.setAdminUser(adminUser);
                request.getSession().setAttribute(AdminUserDto.SESSION_NAME,adminUserDto);
            }
            if(errMsg!=null&&!"".equals(errMsg)){
                return "/login";
            }else{

                return "redirect:/admin";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "admin/login";
    }
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public @ResponseBody String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(AdminUserDto.SESSION_NAME);
        return "1";
    }
}
