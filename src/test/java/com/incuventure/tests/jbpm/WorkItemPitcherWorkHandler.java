package com.incuventure.tests.jbpm;

import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;

public class WorkItemPitcherWorkHandler implements WorkItemHandler {

        public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
            System.out.println("*** [from handler] ***  Executing work item " + workItem);
            manager.completeWorkItem(workItem.getId(), null);
        }

        public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
            System.out.println("*** [from handler] ***  Aborting work item " + workItem);

            manager.abortWorkItem(workItem.getId());
        }
}
