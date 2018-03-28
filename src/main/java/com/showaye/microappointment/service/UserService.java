package com.showaye.microappointment.service;

import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.entity.User;

public interface UserService {

    BaseResult loginByWeChat(String code);

    BaseResult findById(Integer id);


    BaseResult updateUser(User user);

    BaseResult initUser(User user);

    /**
     * 获取我发布的所有活动
     *
     * @param userId
     * @return
     */
    BaseResult getAllMyEvents(Integer userId);

    /**
     * 获取我加入的所有活动
     *
     * @param userId
     * @return
     */
    BaseResult getAllMyAttendEvents(Integer userId);

}
