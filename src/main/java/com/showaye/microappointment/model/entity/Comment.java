package com.showaye.microappointment.model.entity;

import com.showaye.microappointment.model.base.BaseModel;

public class Comment extends BaseModel {
    private static final long serialVersionUID = -4315657160401332131L;

    private Integer id;
    private Integer publisherId;
    private String publisherName;
    private String content;
    private Integer eventId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
