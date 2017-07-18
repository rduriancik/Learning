package com.example.robert.firebasechat.contactlist;

/**
 * Created by robert on 18.7.2017.
 */

interface ContactListInteractor {
    void subscribeForContactEvents();

    void unSubscribeForContactEvents();

    void destroyContactListListener();

    void removeContact(String email);
}
