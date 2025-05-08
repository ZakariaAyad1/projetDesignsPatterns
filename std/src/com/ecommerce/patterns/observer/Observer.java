package com.ecommerce.patterns.observer;

public interface Observer {
    void update(String eventType, Object data);
}