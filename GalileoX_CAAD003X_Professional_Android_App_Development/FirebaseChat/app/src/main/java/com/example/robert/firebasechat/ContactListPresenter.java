package com.example.robert.firebasechat;

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
