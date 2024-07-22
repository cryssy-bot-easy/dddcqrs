package com.incuventure.tests.jbpm;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;

import java.util.Map;

// implement our own send message handler passing along the session
// todo: check if this gets saved when we persist the process instance

public class SendMessageTaskHandler implements WorkItemHandler {

    StatefulKnowledgeSession ksession;

    SendMessageTaskHandler(StatefulKnowledgeSession ksession) {
        this.ksession = ksession;
    }

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        String message = (String) workItem.getParameter("Message");
        System.out.println("Sending message: " + message);
        manager.completeWorkItem(workItem.getId(), null);

        Map<String, Object> mps = workItem.getParameters();
        for(String param : mps.keySet()) {
            System.out.println("[Send Message Parameters] " + workItem.getName() +  " parameter: " + param + " value: " + workItem.getParameter(param));
        }

//        System.out.println("work item name: " + workItem.ge);
//        ksession.signalEvent();
    }

    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        // Do nothing, cannot be aborted
    }

}

