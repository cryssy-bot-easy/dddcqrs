/**
 * 
 */
package com.incuventure.ddd.infrastructure.events;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author Slawek
 * 
 */
@Component
@Target(ElementType.TYPE)
public @interface EventListeners {

}
