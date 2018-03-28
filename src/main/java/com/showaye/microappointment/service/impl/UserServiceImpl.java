package com.showaye.microappointment.service.impl;

import com.showaye.microappointment.cache.CacheManager;
import com.showaye.microappointment.constant.ResultConstant;
import com.showaye.microappointment.dao.UserMapper;
import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.dto.EventGeneralResp;
import com.showaye.microappointment.model.dto.LoginResp;
import com.showaye.microappointment.model.entity.User;
import com.showaye.microappointment.model.entity.WeChatLoginInfo;
import com.showaye.microappointment.service.UserService;
import com.showaye.microappointment.util.WeChatUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public BaseResult loginByWeChat(String code) {

        BaseResult baseResult = new BaseResult();
        // 1. 使用code获取sessionkey
        WeChatLoginInfo loginInfo = WeChatUtil.getSessionKey(code);
        // 2. 查找openId，若存在，即可刷新sessionKey，若不存在，则放入数据库并为用户注册一个账户
        User user = userMapper.findByOpenid(loginInfo.getOpenid());
        if (user != null) {
            // 已存在该用户
            String sessionId = UUID.randomUUID().toString();
            // 将sessionId作为key，sessionKey+openid作为value存入缓存，并设置过期时间
            CacheManager.putSessionKey(sessionId, loginInfo.getSession_key() + loginInfo.getOpenid());
            // 返回sessionId
            baseResult.setData(new LoginResp(user.getId(), sessionId, loginInfo.getOpenid()));
            return baseResult;
        } else {
            // 未存在该用户，则创建用户
            String sessionId = UUID.randomUUID().toString();
            User saveUser = new User();
            saveUser.setOpenid(loginInfo.getOpenid());
            userMapper.insertUser(saveUser);
            baseResult.setData(new LoginResp(saveUser.getId(), sessionId, loginInfo.getOpenid()));
            return baseResult;
        }
    }

    @Override
    public BaseResult findById(Integer id) {

        BaseResult baseResult = new BaseResult();
        User user = userMapper.findById(id);
        baseResult.setData(user);
        return baseResult;
    }

    @Override
    @Transactional
    public BaseResult updateUser(User user) {
        BaseResult baseResult = new BaseResult();
        try {

            int i = userMapper.updateUser(user);
            log.info("updateUser更新了{}行", i);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
            log.error("updateUser异常：{}", e.toString());
        }
        return baseResult;
    }

    @Override
    @Transactional
    public BaseResult initUser(User user) {

        BaseResult baseResult = new BaseResult();
        User userInfo = userMapper.findByOpenid(user.getOpenid());
        if (StringUtils.isBlank(userInfo.getUsername())) {
            // 用户名为空，说明为新增的用户，则更新用户信息
            int i = userMapper.initUser(user);
            log.info("initUser更新了{}行", i);
        }
        return baseResult;
    }

    // @Cacheable("UserServiceImpl.getAllMyEvents")
    @Override
    public BaseResult getAllMyEvents(Integer userId) {

        BaseResult baseResult = new BaseResult();

        List<EventGeneralResp> allMyEvents = userMapper.getAllMyEvents(userId);
        baseResult.setData(allMyEvents);

        return baseResult;
    }

    // @Cacheable("UserServiceImpl.getAllMyAttendEvents")
    @Override
    public BaseResult getAllMyAttendEvents(Integer userId) {
        BaseResult baseResult = new BaseResult();

        List<EventGeneralResp> allMyAttendEvents = userMapper.getAllMyAttendEvents(userId);
        baseResult.setData(allMyAttendEvents);
        return baseResult;
    }
}
