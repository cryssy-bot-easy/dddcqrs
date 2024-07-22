package com.incuventure.tests.commands;

import com.incuventure.cqrs.api.WebAPIHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestWebAPIHandler {

    @WebAPIHandler(handles="testWebAPIMethod")
    public String apiCallHandler(Map params) {
        System.out.println("i am the handler for testWebAPIMethod");
        System.out.println(params);
        return (String) params.get("param1");
    }


}
