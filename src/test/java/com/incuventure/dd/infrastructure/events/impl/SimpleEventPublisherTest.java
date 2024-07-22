package com.incuventure.dd.infrastructure.events.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import com.incuventure.ddd.domain.DomainEvent;
import com.incuventure.ddd.infrastructure.events.impl.SimpleEventPublisher;
import com.incuventure.ddd.infrastructure.events.impl.exception.EventHandlingException;
import com.incuventure.ddd.infrastructure.events.impl.handlers.EventHandler;
import org.junit.Before;
import org.junit.Test;

/**
 */
public class SimpleEventPublisherTest {

    private SimpleEventPublisher eventPublisher;


    @Before
    public void setup(){
        eventPublisher = new SimpleEventPublisher();
    }

    @Test
    public void successfullyPublishAllEventsToRegisteredHandlers(){
        EventHandler mockHandler1 = mock(EventHandler.class);
        when(mockHandler1.canHandle(any(Object.class))).thenReturn(Boolean.TRUE);
        EventHandler mockHandler2 = mock(EventHandler.class);
        when(mockHandler2.canHandle(any(Object.class))).thenReturn(Boolean.TRUE);

        eventPublisher.registerEventHandler(mockHandler1);
        eventPublisher.registerEventHandler(mockHandler2);

        eventPublisher.publish(mock(DomainEvent.class));

        verify(mockHandler1).handle(any(DomainEvent.class));
        verify(mockHandler2).handle(any(DomainEvent.class));

    }

}
