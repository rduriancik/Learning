package com.example.robert.firebasechat.login;

import android.support.annotation.NonNull;

import com.example.robert.firebasechat.User;
import com.example.robert.firebasechat.domain.FirebaseHelper;
import com.example.robert.firebasechat.login.events.LoginEvent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by robert on 14.7.2017.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;
    private DatabaseReference dataReference;
    private DatabaseReference myUserReference;

    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.dataReference = helper.getDataReference();
        this.myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signUp(final String email, final String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        postEvent(LoginEvent.onSignUpSuccess);
                        signIn(email, password);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        postEvent(LoginEvent.onSignUpError, e.getMessage());
                    }
                });
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }

    private void postEvent(int type, String message) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (message != null) {
            loginEvent.setErrorMessage(message);
        }

        EventBus.getDefault().post(loginEvent);
    }

    @Override
    public void signIn(String email, String password) {
        try {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            myUserReference = helper.getMyUserReference();
                            myUserReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    initSignIn(dataSnapshot);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    postEvent(LoginEvent.onSignInError, databaseError.getMessage());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            postEvent(LoginEvent.onSignInError, e.getMessage());
                        }
                    });
        } catch (Exception ex) {
            postEvent(LoginEvent.onSignInError, ex.getMessage());
        }

    }

    private void initSignIn(DataSnapshot dataSnapshot) {
        User currentUser = dataSnapshot.getValue(User.class);

        if (currentUser == null) {
            registerNewUser();
        }

        helper.changeUserConnectionStatus(User.ONLINE);
        postEvent(LoginEvent.onSignInSuccess);
    }

    private void registerNewUser() {
        String email = helper.getAuthUserEmail();
        if (email != null) {
            User currentUser = new User(email, true, null);
            myUserReference.setValue(currentUser);
        }
    }

    @Override
    public void checkAlreadyAuthenticated() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            myUserReference = helper.getMyUserReference();
            myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    initSignIn(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    postEvent(LoginEvent.onSignInError, databaseError.getMessage());
                }
            });
        } else {
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }
}
