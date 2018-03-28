package com.showaye.microappointment.model.entity;

import com.showaye.microappointment.model.base.BaseModel;

public class Like extends BaseModel {

    private static final long serialVersionUID = -8525105381353704767L;

    private Integer id;
    private Integer eventId;
    private Integer userId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
