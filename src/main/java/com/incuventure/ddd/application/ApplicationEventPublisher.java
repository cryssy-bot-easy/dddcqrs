package com.incuventure.ddd.application;

import java.io.Serializable;

public interface ApplicationEventPublisher {

    void publish(Serializable applicationEvent);
}
