package com.incuventure.tests.persistence;

import com.incuventure.tests.persistence.domain.Employee;
import com.incuventure.tests.persistence.infrastructure.repositories.hibernate.HibernateEmployeeRepository;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * User: Jett
 * Date: 6/14/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/engine-test/engineTestContext.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//todo: was getting a No session found for current thread
@TransactionConfiguration(transactionManager = "transactionManager")
//@Transactional
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PersistenceTest {
    //    private Gate gate;

    @Inject
    private HibernateEmployeeRepository employeeRepository;

    @Inject
    private SessionFactory sessionFactory;

    // test Spring 3 embedded database
    private EmbeddedDatabase database;

    @Test
    @Rollback(false)
    public void TestHibernateStuff() {
        Employee employee = new Employee("Jett", "S", "Gamboa");

        employeeRepository.persist(employee);

        Employee emp = employeeRepository.findByLastname("Gamboa");

        sessionFactory.getCurrentSession().flush();

        System.out.println(emp);
    }
}
