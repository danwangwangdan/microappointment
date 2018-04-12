package com.showaye.microappointment.util;

import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 资源文件读取工具
 *
 * @author huangshiming
 * @date 2016年10月15日
 */
public class PropertiesUtilDev {

    // 默认资源文件名称
    private static final String NAME = "appConfig_dev";
    // 缓存时间
    private static final Integer TIME_OUT = 60 * 1000;
    // 当打开多个资源文件时，缓存资源文件
    private static HashMap<String, PropertiesUtilDev> configMap = new HashMap<String, PropertiesUtilDev>();
    // 打开文件时间，判断超时使用
    private Date loadTime = null;
    // 资源文件
    private ResourceBundle resourceBundle = null;

    // 私有构造方法，创建单例
    private PropertiesUtilDev(String name) {
        this.loadTime = new Date();
        this.resourceBundle = ResourceBundle.getBundle(name);
    }

    public static synchronized PropertiesUtilDev getInstance() {
        return getInstance(NAME);
    }

    public static synchronized PropertiesUtilDev getInstance(String name) {
        PropertiesUtilDev conf = configMap.get(name);
        if (null == conf) {
            conf = new PropertiesUtilDev(name);
            configMap.put(name, conf);
        }
        // 判断是否打开的资源文件是否超时1分钟
        if ((System.currentTimeMillis() - conf.getLoadTime().getTime()) > TIME_OUT) {
            conf = new PropertiesUtilDev(name);
            configMap.put(name, conf);
        }
        return conf;
    }

    // 根据key读取value
    public String get(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return "";
        }
    }

    // 根据key读取value(整形)
    public Integer getInt(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Integer.valueOf(value);
        } catch (MissingResourceException e) {
            return null;
        }
    }


    // 根据key读取value(long)
    public Long getLong(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Long.valueOf(value);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    // 根据key读取value(Double)
    public Double getDouble(String key) {
        try {
            String value = resourceBundle.getString(key);
            return Double.valueOf(value);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    // 根据key读取value(布尔)
    public boolean getBool(String key) {
        try {
            String value = resourceBundle.getString(key);
            if ("true".equals(value)) {
                return true;
            }
            return false;
        } catch (MissingResourceException e) {
            return false;
        }
    }

    public Date getLoadTime() {
        return loadTime;
    }

}
