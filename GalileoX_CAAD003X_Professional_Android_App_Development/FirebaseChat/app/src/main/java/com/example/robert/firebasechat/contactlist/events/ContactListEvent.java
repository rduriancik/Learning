package com.example.robert.firebasechat.contactlist.events;

import com.example.robert.firebasechat.models.User;

/**
 * Created by robert on 17.7.2017.
 */

public class ContactListEvent {
    public static final int onContactAdded = 0;
    public static final int onContactChanged = 0;
    public static final int onContactRemoved = 2;

    private User user;
    private int evenType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEvenType() {
        return evenType;
    }

    public void setEvenType(int evenType) {
        this.evenType = evenType;
    }
}
