package com.showaye.microappointment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.showaye.microappointment.constant.DefaultEventPictureUrl;
import com.showaye.microappointment.constant.ResultConstant;
import com.showaye.microappointment.dao.CategoryMapper;
import com.showaye.microappointment.dao.EventMapper;
import com.showaye.microappointment.dao.PictureMapper;
import com.showaye.microappointment.model.base.BaseResult;
import com.showaye.microappointment.model.dto.EventDetailResp;
import com.showaye.microappointment.model.dto.EventGeneralResp;
import com.showaye.microappointment.model.dto.EventResp;
import com.showaye.microappointment.model.dto.LocationReq;
import com.showaye.microappointment.model.entity.*;
import com.showaye.microappointment.service.EventService;
import com.showaye.microappointment.util.MapUtil;
import com.showaye.microappointment.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Service("eventService")
public class EventServiceImpl implements EventService {

    private static Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 加入活动的阻塞队列
     */
    private BlockingQueue<EventAttend> joinQueue = new LinkedBlockingDeque<>();

    //  @Cacheable("findAllCategories")
    @Override
    public BaseResult findAllCategories() {

        BaseResult baseResult = new BaseResult();
        try {
            List<Category> allCategories = eventMapper.findAllCategories();
            baseResult.setData(allCategories);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("findAllCategories错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;

    }

    // @Cacheable("EventServiceImpl.findAllTypes")
    @Override
    public BaseResult findAllTypes() {
        BaseResult baseResult = new BaseResult();
        try {
            List<CategoryDetail> allTypes = eventMapper.findAllTypes();
            baseResult.setData(allTypes);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("findAllTypes错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    // @Cacheable("EventServiceImpl.findTypesByCategoryId")
    @Override
    public BaseResult findTypesByCategoryId(Integer categoryId) {
        BaseResult baseResult = new BaseResult();
        try {
            List<CategoryDetail> types = eventMapper.findTypesByCategoryId(categoryId);
            baseResult.setData(types);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("findTypesByCategoryId错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    // @Cacheable("search")
    @Override
    public BaseResult search(String keyWord, Integer pageNum, Integer pageSize) {
        BaseResult baseResult = new BaseResult();
        if (StringUtils.isBlank(keyWord)) {
            baseResult.setCode(ResultConstant.INPUT_DATA_ERROR.code);
            baseResult.setMessage(ResultConstant.INPUT_DATA_ERROR.message);
            return baseResult;
        }
        try {
            pageNum = pageNum == null ? 1 : pageNum;
            pageSize = pageSize == null ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);
            List<EventGeneralResp> eventGeneralRespList = eventMapper.search(keyWord);
            //用PageInfo对结果进行包装
            PageInfo<EventGeneralResp> pageInfo = new PageInfo<>(eventGeneralRespList);
            baseResult.setData(pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("search错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    //  @Cacheable("findEventGeneralsByTypeId")
    @Override
    public BaseResult findEventGeneralsByTypeId(Integer typeId, Integer pageNum, Integer pageSize) {
        BaseResult baseResult = new BaseResult();
        try {
            pageNum = pageNum == null ? 1 : pageNum;
            pageSize = pageSize == null ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);
            List<EventGeneralResp> eventGeneralRespList = eventMapper.findEventGeneralsByTypeId(typeId);
            //用PageInfo对结果进行包装
            PageInfo<EventGeneralResp> pageInfo = new PageInfo<>(eventGeneralRespList);
            baseResult.setData(pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("findEventGeneralsByTypeId错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    //  @Cacheable("findEventDetailsByEventId")
    @Override
    public BaseResult findEventDetailsByEventId(Integer eventId, Integer userId) {

        BaseResult baseResult = new BaseResult();
        boolean isAttender = false;
        try {
            EventDetailResp eventDetail = eventMapper.findEventDetailsByEventId(eventId);
            // 判断是否为活动参加者
            if (eventDetail.getContracts().size() > 0) {
                for (EventAttend eventAttend : eventDetail.getContracts()) {
                    if (eventAttend.getAttendUserId().intValue() == userId.intValue()) {
                        isAttender = true;
                    }
                }
            }
            // 设置 用户类型
            if (userId.intValue() == eventDetail.getPublisherId().intValue()) {
                eventDetail.setUserType(EventResp.PUBLISHER);
            } else if (isAttender) {
                eventDetail.setUserType(EventResp.ATTENDER);
            } else {
                eventDetail.setUserType(EventResp.VISITOR);
            }
            baseResult.setData(eventDetail);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("findEventDetailsByEventId错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }


    // @CacheEvict(value = {"search", "findEventDetailsByEventId", "findEventGeneralsByTypeId", "UserServiceImpl.getAllMyEvents", "UserServiceImpl.getAllMyAttendEvents"}, allEntries = true)
    @Override
    @Transactional
    public BaseResult publishEvent(Event event) {

        BaseResult baseResult = new BaseResult();
        try {
            // 检查
            if (checkEvent(event, baseResult)) return baseResult;

            int i = eventMapper.insertEvent(event);
            //最新的id
            int lastId = eventMapper.getLastInsertId();
            //插入图片 如果图片为空设置一个默认,否则关联事件后插入图片
            if (event.getPictureUrls() == null || event.getPictureUrls().size() == 0) {
                Picture picture = new Picture();
                picture.setPictureUrl(DefaultEventPictureUrl.PICTURE_OTHERS);
                picture.setEventId(lastId);
                pictureMapper.insertPicture(picture);
            } else {
                for (Picture picture : event.getPictureUrls()) {
                    picture.setEventId(lastId);
                    pictureMapper.insertPicture(picture);
                }
            }

            if (i <= 0) {
                baseResult.setCode(ResultConstant.DATABASE_ERROR.code);
                baseResult.setMessage(ResultConstant.DATABASE_ERROR.message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("publishEvent错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }

        return baseResult;
    }

    //  @CacheEvict(value = {"search", "findEventDetailsByEventId", "findEventGeneralsByTypeId", "UserServiceImpl.getAllMyEvents", "UserServiceImpl.getAllMyAttendEvents"}, allEntries = true)
    @Transactional
    @Override
    public BaseResult join(EventAttend eventAttend) {

        BaseResult baseResult = new BaseResult();
        // 1. 数据校验
        //if (eventAttend.getContractType() == null) {
        //    baseResult.setCode(ResultConstant.INPUT_DATA_ERROR.code);
        //    baseResult.setMessage(ResultConstant.INPUT_DATA_ERROR.message);
        //    return baseResult;
        //}
        try {
            // 2. 人数限制
            Map<String, Integer> numberMap = eventMapper.getLimitNumberByEventId(eventAttend.getEventId());
            Integer limitNumber = numberMap.get("limitNumber"); // 为0 表示不限制人数
            Integer attendNumber = numberMap.get("attendNumber");
            if (limitNumber != 0 && attendNumber + 1 > limitNumber) {
                // 超过人数限制
                baseResult.setCode(ResultConstant.OVER_LIMIT_NUMBER.code);
                baseResult.setMessage(ResultConstant.OVER_LIMIT_NUMBER.message);
                return baseResult;
            }
            // 3. 添加活动参与者信息
            int i = eventMapper.insertEventAttendInfo(eventAttend.getEventId(), eventAttend.getAttendUserId(), eventAttend.getContractType(), eventAttend.getContractNum(), eventAttend.getAttendUsername());
            // 4. 更新活动参与人数
            if (i > 0) {
                eventMapper.updateEventAttendNum(eventAttend.getEventId(), EventAttend.EVENT_JOIN);
            }

            // 5. 如果人数已满，则更新活动人数是否已满状态
            if (limitNumber != 0 && limitNumber == attendNumber + 1) { // 参加人数与限制人数相等，则更新人数是否已满状态
                eventMapper.updateEventIsFull(eventAttend.getEventId(), 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("join错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    // @CacheEvict(value = {"search", "findEventDetailsByEventId", "findEventGeneralsByTypeId", "UserServiceImpl.getAllMyEvents", "UserServiceImpl.getAllMyAttendEvents"}, allEntries = true)
    @Transactional
    @Override
    public BaseResult unjoin(Integer eventId, Integer userId) {

        BaseResult baseResult = new BaseResult();
        Map<String, Integer> numberMap = eventMapper.getLimitNumberByEventId(eventId);
        Integer limitNumber = numberMap.get("limitNumber"); // 为0 表示不限制人数
        Integer attendNumber = numberMap.get("attendNumber");
        try {
            // 1. 删除活动参与者信息
            int i = eventMapper.deleteEventAttendInfo(eventId, userId);
            // 2. 更新活动参与人数
            if (i > 0) {
                eventMapper.updateEventAttendNum(eventId, -1);
            }
            // 3. 如果活动参加人数已满，则需要更新活动人数为未满状态
            if (limitNumber != 0 && limitNumber.intValue() == attendNumber.intValue()) {
                eventMapper.updateEventIsFull(eventId, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("unjoin错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    //  @CacheEvict(value = {"search", "findEventDetailsByEventId", "findEventGeneralsByTypeId", "UserServiceImpl.getAllMyEvents", "UserServiceImpl.getAllMyAttendEvents"}, allEntries = true)
    @Transactional
    @Override
    public BaseResult update(Event event) {

        BaseResult baseResult = new BaseResult();
        try {
            // 检查
            if (checkEvent(event, baseResult)) return baseResult;
            // 更新活动信息
            int i = eventMapper.updateEventById(event);
            //更新图片 如果图片为空设置一个默认,否则关联事件后插入图片（原来的图片不管）
            if (event.getPictureUrls() == null || event.getPictureUrls().size() == 0) {
                Picture picture = new Picture();
                picture.setPictureUrl(DefaultEventPictureUrl.PICTURE_OTHERS);
                picture.setEventId(event.getId());
                pictureMapper.insertPicture(picture);
            } else {
                for (Picture picture : event.getPictureUrls()) {
                    picture.setEventId(event.getId());
                    pictureMapper.insertPicture(picture);
                }
            }


            log.info("update更新了{}行", i);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("update错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    //  @CacheEvict(value = {"search", "findEventDetailsByEventId", "findEventGeneralsByTypeId", "UserServiceImpl.getAllMyEvents", "UserServiceImpl.getAllMyAttendEvents", "UserServiceImpl.getAllMyEvents", "UserServiceImpl.getAllMyAttendEvents"}, allEntries = true)
    @Transactional
    @Override
    public BaseResult updateStatus(Integer status, Integer eventId) {

        BaseResult baseResult = new BaseResult();
        try {
            int i = eventMapper.updateStatusById(status, eventId);
            log.info("updateStatus更新了{}行", i);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("updateStatus错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    @Override
    public BaseResult findNearby(LocationReq locationReq) {

        BaseResult baseResult = new BaseResult();
        try {
            //附近的半径
            int nearRadius = PropertiesUtil.getInstance().getInt("nearRadius");
            double[] around = MapUtil.getAround(Double.valueOf(locationReq.getLatitude()), Double.valueOf(locationReq.getLongitude()), nearRadius);
            locationReq.setMinLat(String.valueOf(around[0]));
            locationReq.setMaxLat(String.valueOf(around[1]));
            locationReq.setMinLng(String.valueOf(around[2]));
            locationReq.setMaxLng(String.valueOf(around[3]));
            List<EventGeneralResp> result = eventMapper.findNearby(locationReq);
            baseResult.setData(result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("findNearby错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }

    @Override
    public BaseResult getLatest() {
        BaseResult baseResult = new BaseResult();
        try {
            List<EventGeneralResp> result = eventMapper.findThreeEventsOrderByTime();
            baseResult.setData(result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("findNearby错误：" + e.toString());
            baseResult.setCode(ResultConstant.SYSTEM_EXCEPTION.code);
            baseResult.setMessage(ResultConstant.SYSTEM_EXCEPTION.message);
        }
        return baseResult;
    }


    private boolean checkEvent(Event event, BaseResult baseResult) {
        if (StringUtils.isBlank(event.getTitle()) || StringUtils.isBlank(event.getLocation())) {
            baseResult.setCode(ResultConstant.INPUT_DATA_ERROR.code);
            baseResult.setMessage(ResultConstant.INPUT_DATA_ERROR.message);
            return true;
        }
        if (StringUtils.isBlank(event.getContent())) {
            event.setContent(event.getTitle());
        }
        Map<String, Integer> typeCategoryMap = categoryMapper.findCategoryIdByTypeId(event.getTypeId());
        event.setCategoryId(typeCategoryMap.get("categoryId"));

        if (event.getLimitNumber() == null || event.getLimitNumber() == 0) {
            event.setLimitNumber(0);
        }

        if (event.getExpireTime() == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, +7);
            event.setExpireTime(calendar.getTime());
        }
        return false;
    }
}
