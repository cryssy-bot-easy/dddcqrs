package com.incuventure.cqrs.infrastructure;

import com.incuventure.cqrs.token.TokenProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Jett
 * Date: 8/4/12
 */
public class StandardTokenProvider implements TokenProvider {

    private Map<String, String> tokenRegistry = new HashMap<String, String>();

    @Override
    public void addTokenForId(String token, String id) {

        // add the token and it's corresponding id to the registry
        tokenRegistry.put(token, id);

    }

    @Override
    public String getIdForToken(String token) {

        // return the id for the given token and remove the token from the registry
        String returnValue = tokenRegistry.get(token);
        tokenRegistry.remove(token);

        return returnValue;

    }
}
