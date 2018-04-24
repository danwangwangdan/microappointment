package com.showaye.microappointment.dao;

import com.showaye.microappointment.model.entity.Picture;
import org.apache.ibatis.annotations.Param;

/**
 * @Author liu pengcheng
 * @Date 2018/4/2.
 * @Email 727910533@qq.com
 */
public interface PictureMapper {
    int insertPicture(Picture picture);

    void deleteByEventId(@Param("eventId") Integer eventId);
}
