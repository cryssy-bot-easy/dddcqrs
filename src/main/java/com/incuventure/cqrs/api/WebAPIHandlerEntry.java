package com.incuventure.cqrs.api;

import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.Method;
import java.util.Map;

public class WebAPIHandlerEntry {

//    private final String apiMethod;
    private final String beanName;
    private final Method method;
    private final BeanFactory beanFactory;

    public WebAPIHandlerEntry(String beanName, Method method, BeanFactory beanFactory) {
//        this.apiMethod = apiMethod;
        this.beanName = beanName;
        this.method = method;
        this.beanFactory = beanFactory;
    }

    public Object handle(Map params) {
        Object bean = beanFactory.getBean(beanName);
        try {
            return method.invoke(bean, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
