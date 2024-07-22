package com.incuventure.ddd.domain;

import javax.persistence.*;

/**
 * 
 * @author Slawek
 * 
 */
@MappedSuperclass
public abstract class BaseEntity {

    // ALWAYS ADD NEW STATUS AT THE END - because the entityStatus field is
    // annotated as ordinal in sake of performance
    public static enum EntityStatus {
        ACTIVE, ARCHIVE
    }

    //entityId because ID can mean something (some domain concept) in some Bounded Context
    @Id
    @GeneratedValue
    private Long entityId;

    @SuppressWarnings("unused")
	@Version
    private Long version;

    @Enumerated(EnumType.ORDINAL)
    private EntityStatus entityStatus = EntityStatus.ACTIVE;

    public void markAsRemoved() {
        entityStatus = EntityStatus.ARCHIVE;
    }

    public Long getEntityId() {
        return entityId;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }
}
