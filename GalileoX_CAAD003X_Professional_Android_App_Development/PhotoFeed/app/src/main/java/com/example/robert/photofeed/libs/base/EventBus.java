package com.example.robert.photofeed.libs.base;

/**
 * Created by robert on 1.9.2017.
 */

public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}
