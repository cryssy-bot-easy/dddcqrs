package com.incuventure.tests.persistence.domain;

import com.incuventure.ddd.domain.annotations.DomainFactory;

@DomainFactory
public class EmployeeFactory {

    public Employee createEmployee(String firstName, String middleName, String lastName) {

        Employee employee = new Employee(firstName, middleName, lastName);
        return employee;

    }

}
