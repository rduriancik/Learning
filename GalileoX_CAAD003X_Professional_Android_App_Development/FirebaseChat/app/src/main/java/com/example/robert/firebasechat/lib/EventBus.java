package com.example.robert.firebasechat.lib;

/**
 * Created by robert on 18.7.2017.
 */

public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}
