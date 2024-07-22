package com.incuventure.tests.queries;

import com.incuventure.cqrs.infrastructure.QueryException;
import com.incuventure.cqrs.infrastructure.StandardQueryBus;
import com.incuventure.cqrs.query.QueryBus;
import com.incuventure.cqrs.query.QueryItem;
import com.incuventure.tests.persistence.domain.Employee;
import com.incuventure.tests.persistence.infrastructure.repositories.hibernate.HibernateEmployeeRepository;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Jett
 * Date: 6/14/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/engine-test/engineTestContext.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class QueryTest {

    @Autowired
    private IEmployeeFinder employeeFinder;

    @Inject
    private HibernateEmployeeRepository employeeRepository;

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    ApplicationContext applicationContext;

    @Inject
    QueryBus queryBus;

    @Before
    public void Setup() {
        Employee employee = new Employee("Jett", "S", "Gamboa");
        employeeRepository.persist(employee);

        Employee employee2 = new Employee("Juan", "S", "Tamad");
        employeeRepository.persist(employee2);

        Employee emp = employeeRepository.findByLastname("Gamboa");
//        System.out.println(emp.getFirstName());

    }

    @Test
    public void testQueryItems() {
        try {
            QueryItem qi = new QueryItem("details", com.incuventure.tests.queries.ITestFinder.class, "findNoArg");
            System.out.println("hello");
        }
        catch(NoSuchMethodException nsme) {
            System.out.println("********* oops");
        }

    }

    @Test
    public void testQueryDispatcher() throws QueryException {

        try {
            List<QueryItem> queryItems = new ArrayList<QueryItem>();

            // create the query and add it to our items to dispatch
            QueryItem qi = new QueryItem("details", com.incuventure.tests.queries.IEmployeeFinder.class, "findAllEmployees");
            QueryItem qi2 = new QueryItem("details2", com.incuventure.tests.queries.IEmployeeFinder.class, "findAllEmployees");
            queryItems.add(qi);
            queryItems.add(qi2);

            HashMap<String, Object> result = queryBus.dispatch(queryItems);

            // find the result
            for(String key : result.keySet()) {
                System.out.println(key);
            }

            List<Map<String, Object>> details = (List<Map<String, Object>>) result.get("details");
            Map<String, Object> firstLine = details.get(0);

            System.out.println("****** " + firstLine.get("FIRSTNAME"));

        }
        catch(NoSuchMethodException nsme) {
            System.out.println("********* oops");
        }

    }

    @Test
    public void engineTest() {;

        Class<?>[] parameterClasses = null;
        Object[] params = null;

        // try to get the method called by the user
        Object instance = applicationContext.getBean("IEmployeeFinder");
        try {
            Method method = instance.getClass().getMethod("findAllEmployees", parameterClasses);
            System.out.println(method.getName());

            Object result = method.invoke(instance, params);

            List employees = (List) result;
            HashMap emp = (HashMap) employees.get(0);
            System.out.println(emp.get("FIRSTNAME"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }

//        for(Method m : instance.getClass().getDeclaredMethods()) {
//            System.out.println(m.getName());
//        }

//        List employees = ((IEmployeeFinder) applicationContext.getBean("IEmployeeFinder")).findAllEmployees();
//        HashMap emp = (HashMap) employees.get(0);

    }


    public void QueryTest() {

        List employees = employeeFinder.findAllEmployees();

        System.out.println(String.valueOf(employees.size()));
        HashMap emp = (HashMap) employees.get(0);
        System.out.println(emp.get("FIRSTNAME"));

    }
}
