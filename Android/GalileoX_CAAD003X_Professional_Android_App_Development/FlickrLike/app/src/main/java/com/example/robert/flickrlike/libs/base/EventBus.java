package com.example.robert.flickrlike.libs.base;

/**
 * Created by robert on 20.8.2017.
 */

public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}
