package com.incuventure.tests.persistence.domain;

import com.incuventure.ddd.domain.annotations.DomainEntity;

import java.io.Serializable;

@DomainEntity
// ToDo: hibernate needs Serializable, see what we can do about this
public class Employee implements Serializable {

    // todo: do we really want this here? remove getter/setter as well
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;

    Employee() {
    }

    public Employee(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName;
    }
}
