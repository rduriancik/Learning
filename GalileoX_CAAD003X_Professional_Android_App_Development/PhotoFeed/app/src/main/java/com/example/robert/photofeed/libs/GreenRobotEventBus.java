package com.example.robert.photofeed.libs;

import com.example.robert.photofeed.libs.base.EventBus;

/**
 * Created by robert on 1.9.2017.
 */

public class GreenRobotEventBus implements EventBus {
    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotEventBus() {
        this.eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
