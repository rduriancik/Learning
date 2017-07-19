package com.example.robert.firebasechat.contactlist;

/**
 * Created by robert on 19.7.2017.
 */

public interface ContactListRepository {
    void signOff();

    String getCurrentEmail();

    void removeContact(String email);

    void destroyContactListListener();

    void subscribeForContactListUpdates();

    void unSubscribeForContactListUpdates();

    void changeUserConnectionStatus(boolean online);
}
