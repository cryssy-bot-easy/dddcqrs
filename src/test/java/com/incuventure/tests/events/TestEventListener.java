package com.incuventure.tests.events;

import com.incuventure.ddd.infrastructure.events.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Geek
 * Date: 6/2/12
 * Time: 11:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TestEventListener {

    @EventListener
    public void handle(TestEvent event) {
        System.out.println("handling test event");
    }

    @EventListener
    public void handlerNumberTwo(TestEvent event) {
        System.out.println("this is the second handler of that event");
    }

    @EventListener
    public void handlerthree(TestEvent event) {
        System.out.println("i am the third one");
    }

}
