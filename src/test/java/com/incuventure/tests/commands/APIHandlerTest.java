package com.incuventure.tests.commands;

import com.incuventure.cqrs.command.CommandBus;
import com.incuventure.cqrs.infrastructure.StandardAPICallDispatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Jett
 * Date: 5/30/12
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/engine-test/engineTestContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class APIHandlerTest {

//    @Autowired
//    HandlerProvider handlerProvider;

    @Autowired
    StandardAPICallDispatcher standardAPICallDispatcher;

    @Autowired
    CommandBus commandBus;

    @Test
    public void testMe() {

        System.out.println("nothing");

        Map<String, String> params = new HashMap<String, String>();
        params.put("param1", "value1");

        Object returnValue = standardAPICallDispatcher.dispatch("testWebAPIMethod", params);

        System.out.println((String) returnValue);

    }
}



