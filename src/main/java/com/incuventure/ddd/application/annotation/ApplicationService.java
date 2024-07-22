/**
 * 
 */
package com.incuventure.ddd.application.annotation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Slawek
 * 
 */
@Service
@Transactional
//@Secured
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApplicationService {
    String value() default "";
}
