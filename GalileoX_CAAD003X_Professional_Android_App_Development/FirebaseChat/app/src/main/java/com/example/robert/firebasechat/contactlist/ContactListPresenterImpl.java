package com.example.robert.firebasechat.contactlist;

import com.example.robert.firebasechat.contactlist.events.ContactListEvent;
import com.example.robert.firebasechat.contactlist.ui.ContactListView;
import com.example.robert.firebasechat.lib.EventBus;
import com.example.robert.firebasechat.lib.GreenRobotEventBus;
import com.example.robert.firebasechat.models.User;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by robert on 18.7.2017.
 */

public class ContactListPresenterImpl implements ContactListPresenter {
    private EventBus eventBus;
    private ContactListView contactListView;
    private ContactListSessionInteractor contactListSessionInteractor;
    private ContactListInteractor contactListInteractor;

    public ContactListPresenterImpl(ContactListView contactListView) {
        this.contactListView = contactListView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.contactListSessionInteractor = null; // FIXME
        this.contactListInteractor = null; //FIXME
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {
        contactListSessionInteractor.changeConnectionStatus(User.ONLINE);
        contactListInteractor.subscribeForContactEvents();
    }

    @Override
    public void onPause() {
        contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactListInteractor.unSubscribeForContactEvents();
        ;
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        contactListInteractor.destroyContactListListener();
        contactListView = null;
    }

    @Override
    public void signOff() {
        contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactListInteractor.destroyContactListListener();
        contactListInteractor.unSubscribeForContactEvents();
        contactListSessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return contactListSessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        contactListInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEvenType()) {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    private void onContactAdded(User user) {
        if (contactListView != null) {
            contactListView.onContactAdded(user);
        }
    }

    private void onContactChanged(User user) {
        if (contactListView != null) {
            contactListView.onContactChanged(user);
        }
    }

    private void onContactRemoved(User user) {
        if (contactListView != null) {
            contactListView.onContactRemoved(user);
        }
    }
}
