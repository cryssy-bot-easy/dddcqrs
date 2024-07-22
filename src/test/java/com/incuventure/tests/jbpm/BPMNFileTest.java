package com.incuventure.tests.jbpm;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * User: Jett
 * Date: 6/15/12
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/engine-test/engineTestContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Ignore
public class BPMNFileTest {

    @Test
    public void testDummyProcess() throws Exception {
//        KnowledgeBase kbase = createKnowledgeBase("REVISED-FXLC Opening - Create eTS.bpmn");
//        KnowledgeBase kbase = createKnowledgeBase("REVISED-process_2.bpmn");
//        KnowledgeBase kbase = createKnowledgeBase("process_1 - HTwithBoundary.bpmn");
//        KnowledgeBase kbase = createKnowledgeBase("REVISED-FXLC Opening - Create eTS.bpmn20.xml");
//        KnowledgeBase kbase = createKnowledgeBase("subprocesstest.bpmn");
                KnowledgeBase kbase = createKnowledgeBase("scriptProcess.bpmn");
        StatefulKnowledgeSession ksession = createKnowledgeSession(kbase);


        // the following can be deleted after testing
        HashMap<String, Object> params = new HashMap<String, Object>();


        TaskDispatcher dispatcher = new TaskDispatcher();
        dispatcher.dispatch("from the outside");
        params.put("dispatcher", dispatcher);

        ksession.startProcess("com.incuventure.test.scriptProcess", params);

    }

    private KnowledgeBase createKnowledgeBase(String process) throws Exception {
        System.out.println("Loading process " + process);
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("engine-test/processmodels/" + process), ResourceType.BPMN2);
//        kbuilder.add(ResourceFactory.newFileResource("C://_project-code//trade//tfs-script-utils/processmodels//" + process), ResourceType.BPMN2);
//        kbuilder.add(ResourceFactory.newFileResource("C:/bpmn-modeler/repository/tfs/" + process), ResourceType.BPMN2);
        return kbuilder.newKnowledgeBase();
    }

    private StatefulKnowledgeSession createKnowledgeSession(KnowledgeBase kbase) {
        return kbase.newStatefulKnowledgeSession();
    }

}

