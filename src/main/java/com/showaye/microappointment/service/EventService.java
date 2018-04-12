package com.showaye.microappointment.service;

import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.dto.LocationReq;
import com.showaye.microappointment.model.entity.Event;
import com.showaye.microappointment.model.entity.EventAttend;

public interface EventService {

    BaseResult findAllCategories();

    BaseResult findAllTypes();

    BaseResult findTypesByCategoryId(Integer categoryId);

    BaseResult search(String keyWord, Integer pageNum, Integer pageSize);

    BaseResult findEventGeneralsByTypeId(Integer typeId, Integer pageNum, Integer pageSize);

    /**
     * 活动详情（不包括已结束）
     *
     * @param eventId
     * @param userId
     * @return
     */
    BaseResult findEventDetailsByEventId(Integer eventId, Integer userId);

    /**
     * 从我的发起进活动详情（所有类型的都能查，包括已结束）
     *
     * @param eventId
     * @param userId
     * @return
     */
    BaseResult findEventDetailsByMyEvent(Integer eventId, Integer userId);

    BaseResult publishEvent(Event event);

    /**
     * 加入一个活动
     *
     * @param eventAttend
     * @return
     */
    BaseResult join(EventAttend eventAttend);

    BaseResult unjoin(Integer eventId, Integer userId);

    /**
     * 更新活动信息
     *
     * @param event
     * @return
     */
    BaseResult update(Event event);

    /**
     * 更新活动状态
     *
     * @param status
     * @param eventId
     * @return
     */
    BaseResult updateStatus(Integer status, Integer eventId);

    BaseResult findNearby(LocationReq locationReq);

    /**
     * 获取最新的三条活动
     *
     * @return
     */
    BaseResult getLatest();

}
