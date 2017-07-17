package com.example.robert.firebasechat;

/**
 * Created by robert on 17.7.2017.
 */

public interface ContactListView {
    void onContactAdded(User user);

    void onContactChanged(User user);

    void onContactRemoved(User user);
}
