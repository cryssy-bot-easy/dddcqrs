package com.incuventure.tests.persistence.infrastructure.repositories.hibernate;

//TEST CODE ONLY

import com.incuventure.tests.persistence.domain.Employee;
import com.incuventure.tests.persistence.domain.EmployeeRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateEmployeeRepository implements EmployeeRepository {

    @Autowired(required=true)
    private SessionFactory sessionFactory;

    @Override
    public Employee persist(Employee employee) {
        return (Employee) this.sessionFactory.getCurrentSession().merge(employee);
    }

    @Override
    public Employee findByLastname(String lastname) {
//        return (Employee) this.sessionFactory.getCurrentSession().createQuery(
//                "from Employee where lastName like ?").setParameter(0, lastname).uniqueResult();

        // Hibernate 4
        return (Employee) this.sessionFactory.getCurrentSession().createQuery(
                "from Employee where lastName like :lastName").setParameter("lastName", lastname).uniqueResult();
    }
}
