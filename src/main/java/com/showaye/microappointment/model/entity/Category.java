package com.showaye.microappointment.model.entity;

import com.showaye.microappointment.model.base.BaseModel;

public class Category extends BaseModel {
    public static final int CATEGORY_MOBILE_GAME = 1;
    public static final int CATEGORY_PC_GAME = 2;
    public static final int CATEGORY_OUTDOOR_ACTIVITY = 3;
    public static final int CATEGORY_OTHERS = 4;
    private static final long serialVersionUID = 4518080799331003862L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
