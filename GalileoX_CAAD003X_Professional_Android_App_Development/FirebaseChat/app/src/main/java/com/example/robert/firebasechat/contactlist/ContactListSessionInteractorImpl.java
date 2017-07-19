package com.example.robert.firebasechat.contactlist;

/**
 * Created by robert on 19.7.2017.
 */

public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {

    private ContactListRepository contactListRepository;

    public ContactListSessionInteractorImpl() {
        this.contactListRepository = new ContactListRepositoryImpl();
    }

    @Override
    public void signOff() {
        contactListRepository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return contactListRepository.getCurrentEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        contactListRepository.changeUserConnectionStatus(online);
    }
}
