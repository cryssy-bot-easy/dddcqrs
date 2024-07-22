package com.incuventure.cqrs.command;

import com.incuventure.cqrs.annotation.CommandHandler;

/**
 * User: Jett
 * Date: 6/1/12
 */
public interface HandlerProvider {

//    public void findCommandHandlerFor(Object command);
    CommandHandler<Object> getHandler(Object command);

}
