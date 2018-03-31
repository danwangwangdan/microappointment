package com.showaye.microappointment.model.dto;

import com.showaye.microappointment.model.base.BaseModel;
import com.showaye.microappointment.model.entity.Picture;

import java.util.Date;
import java.util.List;

/**
 * 活动列表使用的活动信息，只有整个event的部分属性
 */
public class EventGeneralResp extends BaseModel {
    private static final long serialVersionUID = -3024976867885025292L;

    private Integer id;
    private String title;
    private Integer typeId;
    private String typeName;
    /**
     * 活动详细地址
     */
    private String location;
    private Date expireTime;
    /**
     * 活动详情
     */
    private String content;
    private String publisherName;

    private List<Picture> pictureList;

    private Integer likeCount;
    private Integer collectCount;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }
}
