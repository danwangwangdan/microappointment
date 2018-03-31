package com.showaye.microappointment.model.entity;

import com.showaye.microappointment.model.base.BaseModel;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2018/3/31
 */
public class Picture extends BaseModel {

    private static final long serialVersionUID = -4722879565421379171L;

    private int pictureId;
    private int eventId;
    private String pictureUrl;

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
