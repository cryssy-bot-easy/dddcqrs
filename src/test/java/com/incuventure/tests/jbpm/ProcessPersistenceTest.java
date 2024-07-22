package com.incuventure.tests.jbpm;

import bitronix.tm.TransactionManagerServices;
import com.incuventure.jbpm.helper.JBPMHelper;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.persistence.jpa.JPAKnowledgeService;
import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

/**
 * User: Jett
 * Date: 7/2/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/engine-test/engineTestContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProcessPersistenceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testDummyProcess() throws Exception {

        KnowledgeBase kbase = createKnowledgeBase("boundaryTimerProcess.bpmn");
//        StatefulKnowledgeSession ksession = createKnowledgeSession(kbase);

        EntityManagerFactory emf = (EntityManagerFactory) applicationContext.getBean("entityManagerFactoryJbpmPersistanceJpa");

        Environment env = KnowledgeBaseFactory.newEnvironment();
        env.set( EnvironmentName.ENTITY_MANAGER_FACTORY, emf );
        env.set( EnvironmentName.TRANSACTION_MANAGER, applicationContext.getBean("bitronixTransactionManager") );
        env.set( EnvironmentName.TRANSACTION, applicationContext.getBean("bitronixTransactionManager") );
//        env.set( EnvironmentName. GLOBALS, new MapGlobalResolver() );

        Properties properties = new Properties();
        properties.put("drools.processInstanceManagerFactory", "org.jbpm.persistence.processinstance.JPAProcessInstanceManagerFactory");
        properties.put("drools.processSignalManagerFactory", "org.jbpm.persistence.processinstance.JPASignalManagerFactory");
        KnowledgeSessionConfiguration config = KnowledgeBaseFactory.newKnowledgeSessionConfiguration(properties);

        StatefulKnowledgeSession ksession = JPAKnowledgeService.newStatefulKnowledgeSession(kbase, config, env);

        // add a handler for our Human Task
        HumanTaskMockHandler humanTaskMockHandler = new HumanTaskMockHandler();
        ksession.getWorkItemManager().registerWorkItemHandler("Human Task", humanTaskMockHandler);

        ksession.startProcess("BoundaryTimerEventProcess");

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
