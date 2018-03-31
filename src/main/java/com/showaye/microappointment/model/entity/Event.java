package com.showaye.microappointment.model.entity;

import com.showaye.microappointment.model.base.BaseModel;

import java.util.Date;
import java.util.List;


public class Event extends BaseModel {
    private static final long serialVersionUID = -1343717519089863321L;

    private Integer id;
    private String title;

    private int categoryId;
    private String categoryName;
    private String typeName;

    private int typeId;

    /**
     * 活动详细地址
     */
    private String location;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;
    private Date expireTime;
    /**
     * 活动限制人数
     */
    private Integer limitNumber;
    /**
     * 活动详情
     */
    private String content;

    private List<Picture> pictureUrls;
    private Integer publisherId;
    private String attendedCount;
    private Integer likeCount;
    private Integer collectCount;
    private Date createTime;

    private Integer status;
    /**
     * 是否满员
     */
    private Integer isFull;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsFull() {
        return isFull;
    }

    public void setIsFull(Integer isFull) {
        this.isFull = isFull;
    }

    /**
     * 发布者信息
     */
    private User user;

    public List<Picture> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<Picture> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }


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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getAttendedCount() {
        return attendedCount;
    }

    public void setAttendedCount(String attendedCount) {
        this.attendedCount = attendedCount;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
