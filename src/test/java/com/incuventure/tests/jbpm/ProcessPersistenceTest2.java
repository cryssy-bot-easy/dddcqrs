package com.incuventure.tests.jbpm;

import com.incuventure.jbpm.helper.JBPMHelper;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * User: Jett
 * Date: 7/2/12
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/engine-test/engineTestContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProcessPersistenceTest2 {

    @Test
    public void testDummyProcess() throws Exception {

//        KnowledgeBase kbase = createKnowledgeBase("scriptProcess.bpmn");

        startUp();

        KnowledgeBase kbase = createKnowledgeBase("boundaryTimerProcess.bpmn");
//        StatefulKnowledgeSession ksession = JBPMHelper.newStatefulKnowledgeSession(kbase);
        StatefulKnowledgeSession ksession = createKnowledgeSession(kbase);

        // add a handler for our Human Task
        HumanTaskMockHandler humanTaskMockHandler = new HumanTaskMockHandler();
        ksession.getWorkItemManager().registerWorkItemHandler("Human Task", humanTaskMockHandler);

//        ksession.startProcess("com.incuventure.test.scriptProcess");
        ksession.startProcess("BoundaryTimerEventProcess");

        System.out.println("Process started ...");

    }

    private KnowledgeBase createKnowledgeBase(String process) throws Exception {
        System.out.println("Loading process " + process);
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("engine-test/processmodels/" + process), ResourceType.BPMN2);

        return kbuilder.newKnowledgeBase();
    }

    private StatefulKnowledgeSession createKnowledgeSession(KnowledgeBase kbase) {
        return kbase.newStatefulKnowledgeSession();
    }

    private static void startUp() {
//        JBPMHelper.startH2Server();
        JBPMHelper.setupDataSource();
        // please comment this line if you already have the task service running,
        // for example when running the jbpm-installer
//        JBPMHelper.startTaskService();
    }


    // ****** to be used within ******
    private static class HumanTaskMockHandler implements WorkItemHandler {

        private org.drools.runtime.process.WorkItemManager workItemManager;
        private org.drools.runtime.process.WorkItem workItem;

        public void executeWorkItem(org.drools.runtime.process.WorkItem workItem, org.drools.runtime.process.WorkItemManager manager) {
            this.workItem = workItem;
            this.workItemManager = manager;
            System.out.println("Work completed!");
        }

        public void abortWorkItem(org.drools.runtime.process.WorkItem workItem, org.drools.runtime.process.WorkItemManager manager) {
            this.workItemManager.abortWorkItem(workItem.getId());
            System.out.println("Work aborted.");
        }

    }

}
