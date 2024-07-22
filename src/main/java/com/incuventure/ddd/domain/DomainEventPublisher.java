package com.incuventure.ddd.domain;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
