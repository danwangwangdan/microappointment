package com.showaye.microappointment.controller;

import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.dto.LocationReq;
import com.showaye.microappointment.model.entity.Event;
import com.showaye.microappointment.model.entity.EventAttend;
import com.showaye.microappointment.service.EventService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created
 */
@RestController
@RequestMapping("/activity")
public class EventController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    EventService eventService;

    @PostMapping("/publish")
    public BaseResult publish(HttpServletRequest request, @RequestBody Event event) {


        log.info("收到publish请求：" + event.toString());
        BaseResult baseResult = eventService.publishEvent(event);
        log.info("publish返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/category")
    public BaseResult getAllCategories(HttpServletRequest request) {

        log.info("收到getAllCategories请求");
        BaseResult baseResult = eventService.findAllCategories();
        log.info("getAllCategories返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/type")
    public BaseResult getTypesByCategoryId(HttpServletRequest request) {

        log.info("收到getAllTypes请求," + request.getParameter("categoryId"));
        String categoryId = request.getParameter("categoryId");
        if (StringUtils.isBlank(categoryId)) {
            BaseResult baseResult = eventService.findAllTypes();
            log.info("findAllTypes返回信息：" + baseResult.toString());
            return baseResult;
        } else {
            BaseResult baseResult = eventService.findTypesByCategoryId(Integer.valueOf(categoryId));
            log.info("findTypesByCategoryId返回信息：" + baseResult.toString());
            return baseResult;
        }
    }

    @RequestMapping("/item")
    public BaseResult getEventsByTypeId(HttpServletRequest request, @RequestParam Integer typeId, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        log.info("收到getEventsByTypeId请求：" + typeId + " " + pageNum + " " + pageSize);
        BaseResult baseResult = eventService.findEventGeneralsByTypeId(typeId, pageNum, pageSize);
        log.info("getEventsByTypeId返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/itemDetail")
    public BaseResult getItemDetail(HttpServletRequest request, @RequestParam Integer eventId, @RequestParam Integer userId) {

        log.info("收到getItemDetail请求：" + eventId + "  " + userId);
        BaseResult baseResult = eventService.findEventDetailsByEventId(eventId, userId);
        log.info("getItemDetail返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/search")
    public BaseResult search(HttpServletRequest request, @RequestParam String keyWord, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        log.info("收到search请求：" + keyWord + "  " + pageNum + " " + pageSize);
        BaseResult baseResult = eventService.search(keyWord, pageNum, pageSize);
        log.info("search返回信息：" + baseResult.toString());
        return baseResult;
    }

    @PostMapping("/join")
    public BaseResult join(HttpServletRequest request, @RequestBody EventAttend eventAttend) {

        log.info("收到join请求：" + eventAttend.toString());
        BaseResult baseResult = eventService.join(eventAttend);
        log.info("join返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/unjoin")
    public BaseResult unjoin(HttpServletRequest request, @RequestParam Integer eventId, @RequestParam Integer userId) {
        log.info("收到join请求：" + eventId + "  " + userId);
        BaseResult baseResult = eventService.unjoin(eventId, userId);
        log.info("join返回信息：" + baseResult.toString());
        return baseResult;
    }

    @PostMapping("/update")
    public BaseResult update(HttpServletRequest request, @RequestBody Event event) {
        log.info("收到update请求：" + event.toString());
        BaseResult baseResult = eventService.update(event);
        log.info("update返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/updateStatus")
    public BaseResult updateStatus(HttpServletRequest request, @RequestParam Integer status, @RequestParam Integer eventId) {

        log.info("收到updateStatus请求：" + status + "  " + eventId);
        BaseResult baseResult = eventService.updateStatus(status, eventId);
        log.info("updateStatus返回信息：" + baseResult.toString());
        return baseResult;
    }

    @PostMapping("/findNearby")
    public BaseResult findNearby(HttpServletRequest request, @RequestBody LocationReq locationReq) {

        log.info("收到findNearby请求：" + locationReq.toString());
        BaseResult baseResult = eventService.findNearby(locationReq);
        log.info("findNearby返回信息：" + baseResult.toString());
        return baseResult;
    }

    @RequestMapping("/getLatest")
    public BaseResult getLatest(HttpServletRequest request) {

        log.info("收到getLatest请求");
        BaseResult baseResult = eventService.getLatest();
        log.info("getLatest返回信息：" + baseResult.toString());
        return baseResult;
    }
}
