package com.incuventure.tests.commands;

import com.incuventure.cqrs.annotation.Command;

@Command
public class TestCommand {

    public String name;

//    @Override
//    public void validate() {
//        Preconditions.checkNotNull(name, "Name cannot be empty");
//    }
}
