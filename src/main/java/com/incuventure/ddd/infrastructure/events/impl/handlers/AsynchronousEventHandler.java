/**
 * 
 */
package com.incuventure.ddd.infrastructure.events.impl.handlers;

import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.Method;

/**
 * TODO this is just a fake impl, based on standard synchronous impl
 * 
 * @author Slawek
 *
 */
public class AsynchronousEventHandler extends SpringEventHandler{

	/**
	 * @param eventType
	 * @param beanName
	 * @param method
	 * @param beanFactory
	 */
	public AsynchronousEventHandler(Class<?> eventType, String beanName,
			Method method, BeanFactory beanFactory) {
		super(eventType, beanName, method, beanFactory);
	}
	
}
