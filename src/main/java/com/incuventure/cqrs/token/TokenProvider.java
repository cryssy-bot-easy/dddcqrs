package com.incuventure.cqrs.token;

/**
 * User: Jett
 * Date: 8/4/12
 */
public interface TokenProvider {

    public void addTokenForId(String token, String id);
    public String getIdForToken(String token);

}
