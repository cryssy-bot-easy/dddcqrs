package com.incuventure.cqrs.api;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.METHOD)
public @interface WebAPIHandler {

    String handles() default "none";
}
