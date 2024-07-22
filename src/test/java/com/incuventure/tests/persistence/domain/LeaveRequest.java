package com.incuventure.tests.persistence.domain;

import com.incuventure.ddd.domain.BaseAggregateRoot;
import com.incuventure.ddd.domain.annotations.DomainAggregateRoot;

import java.util.Date;

//import com.ipc.leaverequest.domain.events.LeaveRequestSubmittedEvent;

@DomainAggregateRoot
public class LeaveRequest extends BaseAggregateRoot {

    public enum LeaveRequestStatus {
        DRAFT, SUBMITTED, APPROVED, REJECTED
    }

    private Employee employee;

    private Date startDate;
    private Date endDate;

    private LeaveRequestStatus status;

    public LeaveRequestStatus getStatus() {
        return status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    protected LeaveRequest() {

    }

    public LeaveRequest(Employee employee, Date startDate, Date endDate) {
        this.employee = employee;

        this.startDate = startDate;
        this.endDate = endDate;

        this.status = LeaveRequestStatus.DRAFT;
    }


    // business methods (aka: behavior)
    public void submit() {
//        eventPublisher.publish(new LeaveRequestSubmittedEvent());
        //eventPublisher.publish(new LeaveRequestSubmittedEvent event);
    }
}
