package com.example.robert.photofeed.domain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.robert.photofeed.entities.Photo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by robert on 28.8.2017.
 */

public class FirebaseApiHelper {
    private FirebaseAuth mAuth;
    private DatabaseReference mPhotoDatabaseReference;
    private ChildEventListener photosEventListener;

    public FirebaseApiHelper() {
        this.mAuth = FirebaseAuth.getInstance();
        mPhotoDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void checkForData(final FirebaseActionListenerCallback listenerCallback) {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    listenerCallback.onSuccess();
                } else {
                    listenerCallback.onError(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE API", databaseError.getMessage());
            }
        };
        mPhotoDatabaseReference.addValueEventListener(postListener);
    }

    public void subscribe(final FirebaseEventListenerCallback listener) {
        if (photosEventListener == null) {
            photosEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    listener.onChildAdded(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    listener.onChildRemoved(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listener.onCancelled(databaseError);
                }
            };
            mPhotoDatabaseReference.addChildEventListener(photosEventListener);
        }
    }

    public void unsubscribe() {
        mPhotoDatabaseReference.removeEventListener(photosEventListener);
    }

    public String create() {
        return mPhotoDatabaseReference.push().getKey();
    }

    public void update(Photo photo) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(photo.getId()).setValue(photo);
    }

    public void remove(Photo photo, FirebaseActionListenerCallback listener) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(photo.getId()).removeValue();

        listener.onSuccess();
    }

    public String getAuthEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getEmail();
        }

        return null;
    }

    public void signUp(String email, String password, final FirebaseActionListenerCallback listener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        listener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onError(e.getMessage());
                    }
                });
    }

    public void login(String email, String password, final FirebaseActionListenerCallback listenerCallback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        listenerCallback.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listenerCallback.onError(e.getMessage());
                    }
                });
    }

    public void checkForSession(FirebaseActionListenerCallback listenerCallback) {
        if (mAuth.getCurrentUser() != null) {
            listenerCallback.onSuccess();
        } else {
            listenerCallback.onError(null);
        }
    }

    public void logout() {
        mAuth.signOut();
    }
}
