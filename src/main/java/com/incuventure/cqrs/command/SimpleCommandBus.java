package com.incuventure.cqrs.command;

import com.incuventure.cqrs.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * User: Jett
 * Date: 6/1/12
 */
public class SimpleCommandBus implements CommandBus {

    @Autowired
    private HandlerProvider handlerProvider;

    @Override
    public Object dispatch(Object command) {

        CommandHandler<Object> handler = handlerProvider.getHandler(command);

        handler.handle(command);

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
