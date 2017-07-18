package com.example.robert.firebasechat.contactlist;

/**
 * Created by robert on 18.7.2017.
 */

interface ContactListSessionInteractor {
    void signOff();

    String getCurrentUserEmail();

    void changeConnectionStatus(boolean online);
}
