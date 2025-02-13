/**
 * 
 */
package com.incuventure.ddd.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseAggregateRoot extends BaseEntity {

    /**
     * Sample of Domain Event usage<br>
     * Event Publisher is injected by Factory/Repo
     */
    @Transient
    protected DomainEventPublisher eventPublisher;

    /**
     * Sample technique of injecting Event Publisher into the Aggregate.<br>
     * <br>
     * Can be called only once by Factory/Repository<br>
     * Visible for package (Factory/Repository)
     */
    public void setEventPublisher(DomainEventPublisher domainEventPublisher) {
        if (this.eventPublisher != null)
            throw new IllegalStateException("Publisher is already set! Probably You have logical error in code");
        this.eventPublisher = domainEventPublisher;
    }
}
