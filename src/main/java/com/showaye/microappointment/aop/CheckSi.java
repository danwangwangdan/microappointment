package com.showaye.microappointment.aop;

import java.lang.annotation.*;

/**
 * 需要检查sessionId
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckSi {
    /**
     * 仅作标识
     *
     * @return
     */
    String name();
}
