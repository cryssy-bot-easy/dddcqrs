package com.incuventure.cqrs.annotation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * User: Jett
 * Date: 5/31/12
 */
@Component
//@Retention(RetentionPolicy.RUNTIME)
//@Inherited
public interface CommandHandler<C> {

    public void handle(C comand);
}
