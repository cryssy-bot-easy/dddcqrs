package com.incuventure.tests.persistence.domain;

/**
 * User: Jett
 * Date: 6/20/12
 */
public interface EmployeeRepository {
    Employee persist(Employee employee);

    Employee findByLastname(String lastname);
}
