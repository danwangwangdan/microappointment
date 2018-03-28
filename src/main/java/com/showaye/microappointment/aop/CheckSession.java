package com.showaye.microappointment.aop;

import com.showaye.microappointment.util.ClassUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

@Aspect
public class CheckSession {

    private static Logger log = LoggerFactory.getLogger(CheckSession.class);

    @Pointcut(value = "execution(* com.showaye.microappointment.*.*.*(..))")
    public void annotationPoint() {

    }

    @Before(value = "annotationPoint()")
    public void before(JoinPoint joinPoint) {
        //MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //Method method = signature.getMethod();
        //CheckSi annotation = method.getAnnotation(CheckSi.class);

        Set<Class<?>> classes = ClassUtil.getClasses("com.showaye.microappointment");
        for (Class<?> clazz : classes) {

            // 获取类上的注解
            Annotation[] annos = clazz.getAnnotations();
            for (Annotation anno : annos) {

                System.out.println(clazz.getSimpleName().concat(".").concat(anno.annotationType().getSimpleName()));
            }

            // 获取方法上的注解
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    System.out.println(clazz.getSimpleName().concat(".").concat(method.getName()).concat(".")
                            .concat(annotation.annotationType().getSimpleName()));
                }
            }
        }
    }

    private boolean checkSessionId(Annotation annotation) {

        if (annotation.annotationType().hashCode() == CheckSi.class.hashCode()) {


        }
        return true;
    }
}
