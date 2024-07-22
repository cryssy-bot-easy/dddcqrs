package com.incuventure.ddd.infrastructure.events.impl.exception;

/**
 */
public class EventHandlingException extends RuntimeException {

    public EventHandlingException(Throwable e){
        super(e);
    }

    public EventHandlingException(String message,Throwable e){
        super(message,e);
    }
}
