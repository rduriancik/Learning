package com.example.robert.photofeed.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.robert.photofeed.domain.FirebaseActionListenerCallback;
import com.example.robert.photofeed.domain.FirebaseApiHelper;
import com.example.robert.photofeed.libs.base.EventBus;
import com.example.robert.photofeed.login.events.LoginEvent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


/**
 * Created by robert on 28.8.2017.
 */

public class LoginRepositoryImpl implements LoginRepository {
    private EventBus eventBus;
    private FirebaseApiHelper firebase;
    private DatabaseReference dataReference;
    private DatabaseReference myUserReference;

    public LoginRepositoryImpl(EventBus eventBus, FirebaseApiHelper firebase) {
        this.eventBus = eventBus;
        this.firebase = firebase;
    }

    @Override
    public void signUp(final String email, final String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        post(LoginEvent.onSignUpSuccess);
                        signIn(email, password);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        post(LoginEvent.onSignUpError, e.getMessage());
                    }
                });
    }

    @Override
    public void signIn(String email, String password) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            try {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                String email = firebase.getAuthEmail();
                                post(LoginEvent.onSignInSuccess, null, email);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                post(LoginEvent.onSignInError, e.getMessage());
                            }
                        });
            } catch (Exception e) {
                post(LoginEvent.onSignInError, e.getMessage());
            }
        } else {
            firebase.checkForSession(new FirebaseActionListenerCallback() {
                @Override
                public void onSucces() {
                    String email = firebase.getAuthEmail();
                    post(LoginEvent.onSignInSuccess, null, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    post(LoginEvent.onFailedToRecoverSession);
                }
            });
        }
    }


    private void post(int type) {
        post(type, null);
    }

    private void post(int type, String errorMessage) {
        post(type, errorMessage, null);
    }

    private void post(int type, String errorMessage, String loggedUserEmail) {
        LoginEvent event = new LoginEvent();
        event.setEventType(type);
        event.setErrorMessage(errorMessage);
        event.setLoggedUserEmail(loggedUserEmail);
        eventBus.post(event);
    }
}
