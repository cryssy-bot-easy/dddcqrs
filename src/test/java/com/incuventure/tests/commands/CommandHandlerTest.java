package com.incuventure.tests.commands;

import com.incuventure.cqrs.command.CommandBus;
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
public class CommandHandlerTest {

//    @Autowired
//    HandlerProvider handlerProvider;

    @Autowired
    CommandBus commandBus;

    @Test
    public void testMe() {

        TestCommand d = new TestCommand();

//        handlerProvider.getHandler(d);
        commandBus.dispatch(d);

        NoHandlerCommand e = new NoHandlerCommand();
//        handlerProvider.getHandler(e);

//        commandBus.dispatch(e);

    }

}



