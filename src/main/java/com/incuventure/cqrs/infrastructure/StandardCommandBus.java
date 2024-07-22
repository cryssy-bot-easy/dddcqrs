package com.incuventure.cqrs.infrastructure;

import com.incuventure.cqrs.annotation.CommandHandler;
import com.incuventure.cqrs.command.CommandBus;
import com.incuventure.cqrs.command.HandlerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * User: Jett
 * Date: 6/1/12
 */
@Component
public class StandardCommandBus implements CommandBus {

    @Autowired
    private HandlerProvider handlerProvider;

    @Override
    public Object dispatch(Object command) {

        CommandHandler<Object> handler = handlerProvider.getHandler(command);
        handler.handle(command);

        return null;
    }
}
