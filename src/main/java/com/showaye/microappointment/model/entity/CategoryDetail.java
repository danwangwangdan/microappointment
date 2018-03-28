package com.showaye.microappointment.model.entity;

import com.showaye.microappointment.model.base.BaseModel;

public class CategoryDetail extends BaseModel {

    private static final long serialVersionUID = 8911905674974679416L;

    private Integer id;
    /**
     * 活动类别id 参考Category.class常量定义
     */
    private Integer categoryId;
    private String name;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

