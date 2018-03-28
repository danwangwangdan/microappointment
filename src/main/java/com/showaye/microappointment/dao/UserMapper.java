package com.showaye.microappointment.dao;

import com.showaye.microappointment.model.dto.EventGeneralResp;
import com.showaye.microappointment.model.entity.Comment;
import com.showaye.microappointment.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User findByOpenid(@Param("openid") String openid);

    User findById(@Param("id") Integer id);

    int insertUser(User user);

    int updateUser(User user);

    List<EventGeneralResp> getAllMyEvents(@Param("userId") Integer userId);

    List<EventGeneralResp> getAllMyAttendEvents(@Param("userId") Integer userId);


    int addComment(Comment comment);

    int initUser(User user);
}
