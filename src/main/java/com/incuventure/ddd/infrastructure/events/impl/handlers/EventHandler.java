package com.incuventure.ddd.infrastructure.events.impl.handlers;

/**
 * @author Rafał Jamróz
 */
public interface EventHandler {
    boolean canHandle(Object event);

    void handle(Object event);
}