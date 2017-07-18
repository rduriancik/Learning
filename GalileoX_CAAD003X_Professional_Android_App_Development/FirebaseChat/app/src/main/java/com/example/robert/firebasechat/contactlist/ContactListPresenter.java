package com.example.robert.firebasechat.contactlist;

import com.example.robert.firebasechat.contactlist.events.ContactListEvent;

/**
 * Created by robert on 17.7.2017.
 */

public interface ContactListPresenter {
    void onPause();

    void onResume();

    void onCreate();

    void onDestroy();

    void signOff();

    String getCurrentUserEmail();

    void removeContact(String email);

    void onEventMainThread(ContactListEvent event);
}
