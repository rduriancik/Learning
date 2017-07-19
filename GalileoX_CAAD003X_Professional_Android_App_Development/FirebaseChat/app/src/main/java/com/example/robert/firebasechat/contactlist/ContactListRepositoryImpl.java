package com.example.robert.firebasechat.contactlist;

import com.example.robert.firebasechat.contactlist.events.ContactListEvent;
import com.example.robert.firebasechat.domain.FirebaseHelper;
import com.example.robert.firebasechat.lib.GreenRobotEventBus;
import com.example.robert.firebasechat.models.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

class ContactListRepositoryImpl implements ContactListRepository {

    private FirebaseHelper helper;
    private ChildEventListener contactListEventListener;

    public ContactListRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = helper.getAuthUserEmail();
        helper.getOneContactReference(currentUserEmail, email).removeValue();
        helper.getOneContactReference(email, currentUserEmail).removeValue();
    }

    @Override
    public void destroyContactListListener() {
        contactListEventListener = null;
    }

    @Override
    public void subscribeForContactListUpdates() {
        if (contactListEventListener == null) {
            contactListEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_", ".");
                    boolean online = (boolean) dataSnapshot.getValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactAdded, user);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_", ".");
                    boolean online = (boolean) dataSnapshot.getValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactChanged, user);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String email = dataSnapshot.getKey();
                    email = email.replace("_", ".");
                    boolean online = (boolean) dataSnapshot.getValue();
                    User user = new User(email, online, null);
                    postEvent(ContactListEvent.onContactRemoved, user);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
        }

        helper.getMyContactsReference().addChildEventListener(contactListEventListener);
    }

    @Override
    public void unSubscribeForContactListUpdates() {
        if (contactListEventListener != null) {
            helper.getMyContactsReference().removeEventListener(contactListEventListener);
        }
    }

    @Override
    public void changeUserConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }

    private void postEvent(int type, User user) {
        ContactListEvent contactListEvent = new ContactListEvent();
        contactListEvent.setEvenType(type);
        contactListEvent.setUser(user);
        GreenRobotEventBus.getInstance().post(contactListEvent);
    }
}
