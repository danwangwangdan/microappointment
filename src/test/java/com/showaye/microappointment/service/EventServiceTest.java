package com.showaye.microappointment.service;

import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.dto.LocationReq;
import com.showaye.microappointment.model.entity.Event;
import com.showaye.microappointment.model.entity.EventAttend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springContext_dev.xml")
public class EventServiceTest {


    @Autowired
    EventService eventService;

    @Test
    public void findAllCategories() {
        BaseResult baseResult = eventService.findAllCategories();
        System.out.println(baseResult.toString());
    }

    @Test
    public void findAllTypes() {
        BaseResult baseResult = eventService.findAllTypes();
        System.out.println(baseResult.toString());
    }

    @Test
    public void findTypeByCategoryId() {
        BaseResult baseResult = eventService.findTypesByCategoryId(1);
        System.out.println(baseResult.toString());
    }


    @Test
    public void search() {
        BaseResult baseResult = eventService.search("王者荣耀", 1, 10);
        System.out.println(baseResult.toString());
    }

    @Test
    public void join() {
        EventAttend eventAttend = new EventAttend();
        eventAttend.setAttendUserId(1);
        eventAttend.setAttendUsername("raj");
        eventAttend.setContractType(1);
        eventAttend.setEventId(2);
        BaseResult baseResult = eventService.join(eventAttend);
        System.out.println(baseResult.toString());
    }

    @Test
    public void findEventGeneralsByTypeId() {
        BaseResult baseResult = eventService.findEventGeneralsByTypeId(1, 1, 200);
        System.out.println(baseResult.toString());

    }

    @Test
    public void findEventDetailsByEventId() {
        BaseResult baseResult = eventService.findEventDetailsByEventId(1, 1);
        System.out.println(baseResult.toString());
    }

    @Test
    public void findNearby() {
        LocationReq locationReq = new LocationReq();
        locationReq.setLatitude("31.14");
        locationReq.setLongitude("121.55");
        BaseResult baseResult = eventService.findNearby(locationReq);
        System.out.println(baseResult.toString());
    }

    @Test
    public void getLatest() {
        BaseResult baseResult = eventService.getLatest();
        System.out.println(baseResult.toString());
    }


    @Test
    public void publishEvent() {
        Event event = new Event();
        event.setTitle("王者荣耀走起来.....hhhh");
        event.setLocation("齐友佳苑......jjjj");
        event.setLatitude(121.54);
        event.setLongitude(221.45);
       /* Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.MARCH, 5);*/
        event.setExpireTime(new Date(System.currentTimeMillis() + 1000000));
        event.setPublisherId(1);
        event.setTypeId(1);
        System.out.println(event.toString());
        BaseResult baseResult = eventService.publishEvent(event);
        System.out.println(baseResult.toString());
    }
}