package com.incuventure.cqrs.command;

import java.util.Map;

/**
 * User: Jett
 * Date: 5/31/12
 */
public interface CommandBus {

    public abstract Object dispatch(Object command);
}
