package com.incuventure.cqrs.query;

import com.incuventure.cqrs.infrastructure.QueryException;

import java.util.HashMap;
import java.util.List;

/**
 * User: Jett
 * Date: 7/30/12
 */
public interface QueryBus {

    public HashMap<String, Object> dispatch(List<QueryItem> items) throws QueryException;

}
