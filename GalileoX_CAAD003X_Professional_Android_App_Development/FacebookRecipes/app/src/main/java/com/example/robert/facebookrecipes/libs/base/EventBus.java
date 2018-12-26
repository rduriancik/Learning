package com.example.robert.facebookrecipes.libs.base;

/**
 * Created by robert on 3.8.2017.
 */

public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}
