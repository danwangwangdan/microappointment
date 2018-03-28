package com.showaye.microappointment.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheManager {

    /**
     * 缓存过期时间,单位：小时
     */
    private final static long expireTime = 2;

    private static Logger log = LoggerFactory.getLogger(CacheManager.class);


    /**
     * sessionKey的缓存
     */
    private static Cache<String, String> sessionCache = CacheBuilder.newBuilder().maximumSize(20000).expireAfterAccess(expireTime, TimeUnit.HOURS).build();


    /**
     * 获取缓存中的sessionKey
     *
     * @param sessionId sessionId
     * @return sessionKey
     */
    public static String getSessionKey(final String sessionId) {

        String access_token = null;
        try {
            access_token = sessionCache.get(sessionId, new Callable<String>() {

                @Override
                public String call() throws Exception {
                    log.error("accessToken不合法或已过期");
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return access_token;
    }

    /**
     * 将sessionKey存入缓存
     *
     * @param sessionId sessionId
     * @param keyOpenId sessionKey+openId
     */
    public static void putSessionKey(String sessionId, String keyOpenId) {
        log.info("将access_token存入缓存" + sessionId + "," + keyOpenId);
        sessionCache.put(sessionId, keyOpenId);
    }

}
