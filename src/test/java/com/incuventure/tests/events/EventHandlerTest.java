package com.incuventure.tests.events;

import com.incuventure.ddd.domain.DomainEventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created with IntelliJ IDEA.
 * User: Geek
 * Date: 6/2/12
 * Time: 11:52 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/engine-test/engineTestContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class EventHandlerTest {

    @Autowired
//   SimpleEventPublisher simpleEventPublisher;
    DomainEventPublisher eventPublisher;

    @Test
    public void testMe() {

//        TestCommand d = new TestCommand();
//        handlerProvider.findCommandHandlerFor(d);

        System.out.println("publish the event");
        eventPublisher.publish(new TestEvent());
    }
}
