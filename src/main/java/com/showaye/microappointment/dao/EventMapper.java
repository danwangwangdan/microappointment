package com.showaye.microappointment.dao;

import com.showaye.microappointment.model.dto.EventDetailResp;
import com.showaye.microappointment.model.dto.EventGeneralResp;
import com.showaye.microappointment.model.dto.LocationReq;
import com.showaye.microappointment.model.entity.Category;
import com.showaye.microappointment.model.entity.CategoryDetail;
import com.showaye.microappointment.model.entity.Event;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EventMapper {


    List<Category> findAllCategories();

    List<CategoryDetail> findAllTypes();

    List<CategoryDetail> findTypesByCategoryId(@Param("categoryId") Integer categoryId);

    List<EventGeneralResp> search(@Param("keyWord") String keyWord);

    List<EventGeneralResp> findEventGeneralsByTypeId(@Param("typeId") Integer typeId);

    EventDetailResp findEventDetailsByEventId(@Param("eventId") Integer eventId);

    int insertEvent(Event event);

    int insertEventAttendInfo(@Param("eventId") Integer eventId, @Param("attenderId") Integer attenderId, @Param("contractType") Integer contractType, @Param("contractNum") String contractNum, @Param("attenderUsername") String attenderUsername);

    int updateEventAttendNum(@Param("eventId") Integer eventId, @Param("eventJoin") int eventJoin);

    Map<String, Integer> getLimitNumberByEventId(@Param("eventId") Integer eventId);

    int updateEventIsFull(@Param("eventId") Integer eventId, @Param("isFull") Integer isFull);

    int deleteEventAttendInfo(@Param("eventId") Integer eventId, @Param("userId") Integer userId);

    int updateEventById(Event event);

    int updateStatusById(@Param("status") Integer status, @Param("eventId") Integer eventId);

    List<EventGeneralResp> findNearby(LocationReq locationReq);

    List<EventGeneralResp> findThreeEventsOrderByTime();

}
