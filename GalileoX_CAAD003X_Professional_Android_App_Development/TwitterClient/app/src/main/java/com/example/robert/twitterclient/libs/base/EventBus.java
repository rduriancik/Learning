package com.example.robert.twitterclient.libs.base;

/**
 * Created by robert on 23.7.2017.
 */

public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}
