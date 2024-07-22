package com.incuventure.tests.commands;

import com.incuventure.cqrs.annotation.CommandHandler;
import org.springframework.stereotype.Component;

/**
 * User: Jett
 * Date: 5/30/12
 */
@Component
public class TestCommandHandler implements CommandHandler<TestCommand>{

    @Override
    public void handle(TestCommand comand) {
        System.out.println("I am the command handler");

    }

//    public Long dummyHandler(TestCommand testCommand) {
//
//        System.out.println("I am the command handler");
//        return 1L;
//    }
}
