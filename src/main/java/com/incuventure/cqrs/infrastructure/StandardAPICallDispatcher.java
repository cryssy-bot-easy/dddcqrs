package com.incuventure.cqrs.infrastructure;

import com.incuventure.cqrs.api.WebAPICallDispatcher;
import com.incuventure.cqrs.api.WebAPIHandlerEntry;
import com.incuventure.ddd.infrastructure.events.impl.handlers.EventHandler;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StandardAPICallDispatcher implements WebAPICallDispatcher {

//    private Set<WebAPIHandlerEntry> apiHandlers = new HashSet<WebAPIHandlerEntry>();
    private Map<String, WebAPIHandlerEntry> apiHandlers = new HashMap<String, WebAPIHandlerEntry>();

//    public void registerCallHandler(WebAPIHandlerEntry handler) {
//        apiHandlers.add(handler);
//    }
    public void  registerCallHandler(String apiMethod, WebAPIHandlerEntry handler) {
        apiHandlers.put(apiMethod, handler);
    }

    @Override
    public Object dispatch(String apiMethod, Map params) {

        WebAPIHandlerEntry handler = apiHandlers.get(apiMethod);

        if(handler != null) {
            return handler.handle(params);
        }

        return null;
    }
}
