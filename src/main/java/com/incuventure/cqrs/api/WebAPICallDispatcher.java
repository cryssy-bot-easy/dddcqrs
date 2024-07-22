package com.incuventure.cqrs.api;

import java.util.Map;

public interface WebAPICallDispatcher {

    public Object dispatch(String apiCall, Map params);

}
