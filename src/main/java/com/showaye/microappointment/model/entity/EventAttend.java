package com.showaye.microappointment.model.entity;

import com.showaye.microappointment.model.base.BaseResult;

/**
 * 参加活动的人的相关信息
 */
public class EventAttend extends BaseResult {


    public static final int CONTRACT_TYPE_MOBILE = 1;
    public static final int CONTRACT_TYPE_WX = 2;
    public static final int CONTRACT_TYPE_QQ = 3;

    /**
     * 加入活动
     */
    public static final int EVENT_JOIN = 1;
    /**
     * 取消加入活动
     */
    public static final int EVENT_UNJOIN = -1;
    private static final long serialVersionUID = 4646574806749858958L;

    private Integer id;
    private Integer eventId;
    private Integer attendUserId;
    private String attendUsername;
    private Integer contractType;
    private String contractNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public Integer getAttendUserId() {
        return attendUserId;
    }

    public void setAttendUserId(Integer attendUserId) {
        this.attendUserId = attendUserId;
    }

    public String getAttendUsername() {
        return attendUsername;
    }

    public void setAttendUsername(String attendUsername) {
        this.attendUsername = attendUsername;
    }
}
