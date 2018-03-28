package com.showaye.microappointment.controller;

import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.entity.User;
import com.showaye.microappointment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/loginByWx")
    public BaseResult loginByWx(@RequestParam String code, HttpServletRequest request) {

        log.info("收到loginByWx请求：" + code);
        BaseResult baseResult = userService.loginByWeChat(code);
        log.info("loginByWx返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public BaseResult initUserInfo(@RequestBody User user, HttpServletRequest request) {

        log.info("收到initUserInfo请求：" + user.toString());
        BaseResult baseResult = userService.initUser(user);
        log.info("init返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult updateUserInfo(@RequestBody User user, HttpServletRequest request) {

        log.info("收到updateUserInfo请求：" + user.toString());
        BaseResult baseResult = userService.updateUser(user);
        log.info("updateUserInfo返回信息：" + baseResult.toString());
        return baseResult;
    }


    @RequestMapping("/find")
    public BaseResult getUserInfo(@RequestParam Integer userId, HttpServletRequest request) {

        log.info("收到find请求：" + userId);
        BaseResult baseResult = userService.findById(userId);
        log.info("find返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/allMyPublished")
    public BaseResult getAllMyPublished(HttpServletRequest request, @RequestParam Integer userId) {
        log.info("收到allMyPublished请求:" + userId);
        BaseResult baseResult = userService.getAllMyEvents(userId);
        log.info("allMyPublished返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/allMyAttended")
    public BaseResult getAllMyAttended(HttpServletRequest request, @RequestParam Integer userId) {
        log.info("收到allMyAttended请求:" + userId);
        BaseResult baseResult = userService.getAllMyAttendEvents(userId);
        log.info("allMyAttended返回信息：" + baseResult.toString());
        return baseResult;
    }
}
