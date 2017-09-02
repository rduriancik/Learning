package com.example.robert.photofeed.domain;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by robert on 28.8.2017.
 */

public interface FirebaseEventListenerCallback {
    void onChildAdded(DataSnapshot dataSnapshot);

    void onChildRemoved(DataSnapshot dataSnapshot);

    void onCancelled(DatabaseError error);
}
