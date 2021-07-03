package com.sp.fc.web.config;

import com.sp.fc.web.controller.PaperController;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.log.LogMessage;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.prepost.PrePostAnnotationSecurityMetadataSource;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

public class CustomMetadataSource implements MethodSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {

        PrePostAnnotationSecurityMetadataSource prePostAnnotationSecurityMetadataSource;

        //constrollerdp  @Secured({"SCHOOL_PRIMARY"}를 선언 하지 않아도 custom voter를 찾아오게됨
//        if (method.getName().equals("getPapersByPrimary") && targetClass == PaperController.class){
//            return List.of(new SecurityConfig("SCHOOL_PRIMARY"));
//        }

        //custom annotation 사용
        CustomSecurityTag annotation = findAnnotation(method, targetClass, CustomSecurityTag.class);
        if (annotation != null) {
            return List.of(new SecurityConfig("SCHOOL_PRIMARY"));
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MethodInvocation.class.isAssignableFrom(clazz);
    }

    private <A extends Annotation> A findAnnotation(Method method, Class<?> targetClass, Class<A> annotationClass) {
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        A annotation = AnnotationUtils.findAnnotation(specificMethod, annotationClass);
        if (annotation != null) {

            return annotation;
        }
        return annotation;
    }
}
