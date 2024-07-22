package com.incuventure.cqrs.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * User: Jett
 * Date: 5/31/12
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Command {

    boolean asynchronous() default false;
    boolean unique() default false;

}
