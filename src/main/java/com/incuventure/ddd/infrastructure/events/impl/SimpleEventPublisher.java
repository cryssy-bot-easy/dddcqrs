package com.incuventure.ddd.infrastructure.events.impl;

import com.incuventure.ddd.application.ApplicationEventPublisher;
import com.incuventure.ddd.domain.DomainEvent;
import com.incuventure.ddd.domain.DomainEventPublisher;
import com.incuventure.ddd.infrastructure.events.impl.handlers.EventHandler;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class SimpleEventPublisher implements DomainEventPublisher, ApplicationEventPublisher {

    private Set<EventHandler> eventHandlers = new HashSet<EventHandler>();

    public void registerEventHandler(EventHandler handler) {
        eventHandlers.add(handler);
        // new SpringEventHandler(eventType, beanName, method));
    }

    @Override
    public void publish(Serializable event) {
        doPublish(event);
    }

    @Override
    public void publish(DomainEvent event) {
        doPublish(event);
    }

    protected void doPublish(Object event) {
        for (EventHandler handler : new ArrayList<EventHandler>(eventHandlers)) {
            if (handler.canHandle(event)) {
                handler.handle(event);
            }
        }
    }
}
