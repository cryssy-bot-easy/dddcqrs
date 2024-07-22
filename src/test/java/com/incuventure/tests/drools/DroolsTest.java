package com.incuventure.tests.drools;

import com.incuventure.tests.jbpm.TaskDispatcher;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.type.FactType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * User: Jett
 * Date: 6/15/12
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/engine-test/engineTestContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Ignore
public class DroolsTest {

    @Test
    public void testDummyProcess() throws Exception {
        KnowledgeBase kbase = createKnowledgeBase("sampleRule.drl");
        StatefulKnowledgeSession ksession = createKnowledgeSession(kbase);

        FactType serviceTransactionFactType =kbase.getFactType("com.ucpb.tfs.rules.charges", "ServiceTransaction");

        Object serviceTransaction = serviceTransactionFactType.newInstance();

        serviceTransactionFactType.set(serviceTransaction, "product", "LC");
        serviceTransactionFactType.set(serviceTransaction, "documentType", "FOREIGN");
        serviceTransactionFactType.set(serviceTransaction, "subType1", "CASH");
        serviceTransactionFactType.set(serviceTransaction, "subType2", "");
        serviceTransactionFactType.set(serviceTransaction, "serviceType", "OPENING");

        ksession.insert(serviceTransaction);

        ksession.fireAllRules();

    }

    private KnowledgeBase createKnowledgeBase(String process) throws Exception {
        System.out.println("Loading process " + process);
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("engine-test/rules/" + process), ResourceType.DRL);
        return kbuilder.newKnowledgeBase();
    }

    private StatefulKnowledgeSession createKnowledgeSession(KnowledgeBase kbase) {
        return kbase.newStatefulKnowledgeSession();
    }

}

